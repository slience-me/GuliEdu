package xyz.slienceme.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import xyz.slienceme.eduservice.entity.EduSubject;
import xyz.slienceme.eduservice.entity.excel.SubjectData;
import xyz.slienceme.eduservice.listener.SubjectExcelListener;
import xyz.slienceme.eduservice.mapper.EduSubjectMapper;
import xyz.slienceme.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author slience_me
 * @since 2024-01-26
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
