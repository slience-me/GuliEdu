package xyz.slienceme.eduservice.mapper;

import xyz.slienceme.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.slienceme.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author slience_me
 * @since 2024-01-26
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {


    public CoursePublishVo getPublishCourseInfo(String courseId);
}
