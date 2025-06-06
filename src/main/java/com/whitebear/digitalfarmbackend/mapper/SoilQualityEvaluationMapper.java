package com.whitebear.digitalfarmbackend.mapper;

import com.whitebear.digitalfarmbackend.model.dto.SoilQualityEvaluationDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SoilQualityEvaluationMapper {

    @Select({
        "<script>",
        "SELECT e.evaluation_id as evaluationId, e.point_id as pointId, m.point_name as pointName,",
        "       b.base_name as baseName, e.quality_level as qualityLevel,",
        "       e.evaluation_date as evaluationDate, e.evaluator as evaluator, e.remarks as remarks,",
        "       e.create_time as createTime",
        "FROM SoilQualityEvaluation e",
        "LEFT JOIN MonitoringPoint m ON e.point_id = m.point_id",
        "LEFT JOIN Base b ON m.base_id = b.base_id",
        "WHERE 1=1",
        "<if test='baseId != null and baseId != \"\"'>",
        "  AND b.base_id = #{baseId}",
        "</if>",
        "<if test='keyword != null and keyword != \"\"'>",
        "  AND (e.remarks LIKE CONCAT('%', #{keyword}, '%')",
        "       OR m.point_name LIKE CONCAT('%', #{keyword}, '%')",
        "       OR b.base_name LIKE CONCAT('%', #{keyword}, '%'))",
        "</if>",
        "LIMIT #{pageSize} OFFSET #{offset}",
        "</script>"
    })
    List<SoilQualityEvaluationDTO> selectByConditions(
        @Param("baseId") String baseId,
        @Param("keyword") String keyword,
        @Param("offset") int offset,
        @Param("pageSize") int pageSize
    );

    @Select({
        "<script>",
        "SELECT COUNT(*) FROM SoilQualityEvaluation e",
        "LEFT JOIN MonitoringPoint m ON e.point_id = m.point_id",
        "LEFT JOIN Base b ON m.base_id = b.base_id",
        "WHERE 1=1",
        "<if test='baseId != null and baseId != \"\"'>",
        "  AND b.base_id = #{baseId}",
        "</if>",
        "<if test='keyword != null and keyword != \"\"'>",
        "  AND (e.remarks LIKE CONCAT('%', #{keyword}, '%')",
        "       OR m.point_name LIKE CONCAT('%', #{keyword}, '%')",
        "       OR b.base_name LIKE CONCAT('%', #{keyword}, '%'))",
        "</if>",
        "</script>"
    })
    long countByConditions(@Param("baseId") String baseId, @Param("keyword") String keyword);

    @Select("SELECT COUNT(*) FROM SoilQualityEvaluation WHERE evaluation_id = #{evaluationId}")
    int checkEvaluationExists(@Param("evaluationId") Integer evaluationId);

    @Delete("DELETE FROM SoilQualityEvaluation WHERE evaluation_id = #{evaluationId}")
    int deleteSoilQualityEvaluation(@Param("evaluationId") Integer evaluationId);

    @Insert({
        "INSERT INTO SoilQualityEvaluation (point_id, quality_level, evaluation_date, evaluator, remarks, create_time)",
        "VALUES (#{pointId}, #{qualityLevel}, #{evaluationDate}, #{evaluator}, #{remarks}, NOW())"
    })
    @Options(useGeneratedKeys = true, keyProperty = "evaluationId")
    int insertSoilQualityEvaluation(SoilQualityEvaluationDTO soilQualityEvaluationDTO);

    @Update({
        "<script>",
        "UPDATE SoilQualityEvaluation",
        "<set>",
        "  <if test='pointId != null'>point_id = #{pointId},</if>",
        "  <if test='qualityLevel != null'>quality_level = #{qualityLevel},</if>",
        "  <if test='evaluationDate != null'>evaluation_date = #{evaluationDate},</if>",
        "  <if test='evaluator != null and evaluator != \"\"'>evaluator = #{evaluator},</if>",
        "  <if test='remarks != null and remarks != \"\"'>remarks = #{remarks},</if>",

        "</set>",
        "WHERE evaluation_id = #{evaluationId}",
        "</script>"
    })
    int updateSoilQualityEvaluation(SoilQualityEvaluationDTO soilQualityEvaluationDTO);
}
