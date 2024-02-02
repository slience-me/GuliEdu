package xyz.slienceme.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.slienceme.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.slienceme.eduservice.entity.vo.TeacherQuery;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author slience_me
 * @since 2024-01-26
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);


    //1 分页查询讲师的方法
    Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);

}
