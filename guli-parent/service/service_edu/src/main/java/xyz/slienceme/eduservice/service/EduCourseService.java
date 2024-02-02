package xyz.slienceme.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.slienceme.eduservice.entity.EduCourse;
import xyz.slienceme.eduservice.entity.frontvo.CourseFrontVo;
import xyz.slienceme.eduservice.entity.frontvo.CourseWebVo;
import xyz.slienceme.eduservice.entity.vo.CourseInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.slienceme.eduservice.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author slience_me
 * @since 2024-01-26
 */
public interface EduCourseService extends IService<EduCourse> {

    //添加课程基本信息的方法
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程基本信息
    CourseInfoVo getCourseInfo(String courseId);

    //修改课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程确认信息
    CoursePublishVo publishCourseInfo(String id);

    //1 条件查询带分页查询课程前台
    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    //删除课程
    void removeCourse(String courseId);

    //根据课程id，编写sql语句查询课程信息
    CourseWebVo getBaseCourseInfo(String courseId);
}
