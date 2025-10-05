package com.ruoyi.newfront.dao.mapper;

import cn.hutool.core.util.StrUtil;
import com.github.yulichang.base.MPJBaseMapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.ruoyi.newfront.dao.entity.Option;
import com.ruoyi.newfront.dao.entity.Question;
import com.ruoyi.newfront.dao.entity.User;
import com.ruoyi.newfront.dto.resp.QuestionRespDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface QuestionMapper extends MPJBaseMapper<Question> {

    /**
     * 一次性查出：题目 + 创建人 + 标签 + 选项
     */
    default List<QuestionRespDTO> selectListWithTagsAndOptions(Long repoId) {
        // 1. 主查询：题目、创建人、标签
        MPJLambdaWrapper<Question> wrapper = new MPJLambdaWrapper<>(Question.class)
                .selectAll(Question.class)
                .select("u.user_name AS create_name")
                .select("GROUP_CONCAT(t2.tag ORDER BY t2.tag SEPARATOR ',') AS tag_str")
                .leftJoin(User.class, "u", User::getUserId, Question::getCreateId)
                .leftJoin("t_question_question_tag qt ON qt.question_id = t.id")
                .leftJoin("t_question_tag t2 ON t2.tag_id = qt.tag_id")
                .eq(Question::getRepoId, repoId)
                .eq(Question::getDeleted, 0)
                .groupBy("t.id");

        List<QuestionRespDTO> list = this.selectJoinList(QuestionRespDTO.class, wrapper);

        if (list.isEmpty()) return list;

        // 2. 一次性查出所有选项
        Set<Long> qIds = list.stream().map(QuestionRespDTO::getId).collect(Collectors.toSet());
        List<Option> optList = this.selectOptionsByQuestionIds(qIds);

        // 3. 按 questionId 分组
        Map<Long, List<Option>> optMap = optList.stream()
                .collect(Collectors.groupingBy(Option::getQuestionId));

        // 4. 回填
        list.forEach(q -> {
            q.setTags(StrUtil.split(q.getTagStr() == null ? "" : q.getTagStr(), ',', true, true));
            q.setOptions(optMap.getOrDefault(q.getId(), Collections.emptyList()));
        });

        return list;
    }

    /**
     * 批量查选项
     */
    @Select({
            "<script>",
            "SELECT id, question_id, option_label, content, sort_order",
            "  FROM t_question_option",
            " WHERE question_id IN",
            "   <foreach collection='qIds' item='id' open='(' separator=',' close=')'>",
            "     #{id}",
            "   </foreach>",
            " ORDER BY question_id, sort_order",
            "</script>"
    })
    List<Option> selectOptionsByQuestionIds(@Param("qIds") Set<Long> qIds);
}