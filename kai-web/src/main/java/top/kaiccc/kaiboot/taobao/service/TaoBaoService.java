package top.kaiccc.kaiboot.taobao.service;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.kaiccc.kaiboot.common.exception.RestException;
import top.kaiccc.kaiboot.taobao.dto.TaoBaoCodeDto;
import top.kaiccc.kaiboot.taobao.dto.TaoBaoCommentDto;
import top.kaiccc.kaiboot.taobao.dto.TaoBaoDto;
import top.kaiccc.kaiboot.taobao.entity.Code;
import top.kaiccc.kaiboot.taobao.entity.Comment;
import top.kaiccc.kaiboot.taobao.entity.Pics;
import top.kaiccc.kaiboot.taobao.repository.CommentRepository;
import top.kaiccc.kaiboot.taobao.repository.PicsRepository;
import top.kaiccc.kaiboot.taobao.repository.TaoBaoRepository;

import java.io.File;
import java.util.List;

/**
 * @author kaiccc
 * @date 2018-11-08 16:34
 */
@Slf4j
@Service
public class TaoBaoService {

    @Value("${file.taobao.imagePath}")
    public String imagePath;
    public static final String IMG_REG = ".+(.JPEG|.jpeg|.JPG|.jpg|.png|.PNG)$";
    @Autowired
    public TaoBaoRepository taoBaoRepository;
    @Autowired
    public PicsRepository picsRepository;
    @Autowired
    public CommentRepository commentRepository;

    private static final Gson GSON = new Gson();

    public void save(TaoBaoDto taoBao){

        if (taoBaoRepository.countBySellerId(taoBao.getSellerId()) > 0){
            log.info("此店铺已经收集过了, {} {}", taoBao.getSellerId(), taoBao.getSellerName());
            throw new RestException(StrUtil.format("此店铺已经收集过了, {} {}", taoBao.getSellerId(), taoBao.getSellerName()));
        }
        // code 内容保存本地文件
        File sellerRootFile = FileUtil.mkdir(imagePath + File.separator + taoBao.getSellerName());
        String sellerCodeFile = sellerRootFile.getPath() + File.separator + taoBao.getSellerName();
        log.info(sellerCodeFile);

        FileWriter baseWriter = new FileWriter( sellerCodeFile + "_base.json");
        baseWriter.write(taoBao.getCode());
        log.debug("原始code 保存成功 base.json");
        String codeBase64 = Base64.decodeStr(taoBao.getCode(), "UTF-8");

        List<List<TaoBaoCodeDto>> codeList = GSON.fromJson(codeBase64, new TypeToken<List<List<TaoBaoCodeDto>>>() {
        }.getType());

        FileWriter codeWriter = new FileWriter( sellerCodeFile + ".json");
        codeWriter.write(GSON.toJson(codeBase64));
        log.debug("解码后的code 保存成功 .json");

        log.debug("一共 {} 页数据，总数据：{}", codeList.size(), codeList.size() * 20);
        int k = 1;
        for (List<TaoBaoCodeDto> page : codeList) {
            for (TaoBaoCodeDto tb : page) {
                if (StrUtil.isEmpty(tb.getId())) {
                    continue;
                }
                try {
                    log.debug("第{}条数据开始处理", k);

                    String filePath = sellerRootFile + File.separator + tb.getId();
                    FileWriter writer = new FileWriter( filePath + ".json");
                    writer.write(GSON.toJson(tb));

                    Code code = new Code();
                    code.setSellerName(taoBao.getSellerName());
                    code.setCodeId(tb.getId());
                    code.setTargetUrl(tb.getTargetUrl());
                    code.setTitle(tb.getTitle());
                    code.setSellerId(taoBao.getSellerId());

                    taoBaoRepository.save(code);

                    int i = 1;
                    for (TaoBaoCodeDto.PicsBean pics : tb.getPics()) {
                        String fileName = StrUtil.format("{}_{}{}", tb.getId(), i, ReUtil.get(IMG_REG, pics.getPath(), 1));

                        Pics picsEntity = new Pics();
                        picsEntity.setCodeId(code.getId());
                        picsEntity.setFileName(fileName);
                        picsEntity.setFilePath(sellerRootFile + File.separator + fileName);
                        picsEntity.setPath(pics.getPath());
                        picsEntity.setCompleted(false);
                        picsEntity.setTitle(tb.getTitle());

                        picsRepository.save(picsEntity);
                        i++;
                    }

                    if (!StrUtil.equals(tb.getCommentCount(), "0")
                            && ObjectUtil.isNotNull(tb.getIsTop())
                            && ObjectUtil.isNotNull(tb.getIsTop().getList())) {

                        for (TaoBaoCommentDto.ListBean list : tb.getIsTop().getList()) {
                            Comment comment = new Comment();
                            comment.setCodeId(code.getId());
                            comment.setCommenterNick(list.getCommenterNick());
                            comment.setContent(list.getContent());
                            commentRepository.save(comment);
                        }
                    }
                }catch (Exception e){
                    log.error("保存数据失败：", e);
                }
                k++;
            }
        }
        log.debug("TB分析数据存储完成。保存的总数据：{}", k);
    }

}
