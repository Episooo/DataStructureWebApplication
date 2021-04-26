package cn.episooo.datastructurewebapplication.service;

import cn.episooo.datastructurewebapplication.domain.Question;
import cn.episooo.datastructurewebapplication.domain.RecommendAnswer;
import cn.episooo.datastructurewebapplication.domain.Tag;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.similarity.precompute.example.GroupLensDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/4/18 15:13
 * @Description：
 */
@Component
public class RecommendService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) throws Exception {
//        //准备数据 这里是电影评分数据
//        File file = new File("C:\\Users\\Episo\\Desktop\\ratings.dat");
//        //将数据加载到内存中，GroupLensDataModel是针对开放电影评论数据的
//        DataModel dataModel = new GroupLensDataModel(file);
//        //计算相似度，相似度算法有很多种，欧几里得、皮尔逊等等。
//        UserSimilarity similarity = new EuclideanDistanceSimilarity(dataModel);
//        //计算最近邻域，邻居有两种算法，基于固定数量的邻居和基于相似度的邻居，这里使用基于固定数量的邻居
//        UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(5, similarity, dataModel);
//        //构建推荐器，协同过滤推荐有两种，分别是基于用户的和基于物品的，这里使用基于用户的协同过滤推荐
//        Recommender recommender = new GenericUserBasedRecommender(dataModel, userNeighborhood, similarity);
//        //给用户ID等于5的用户推荐10部电影
//        List<RecommendedItem> recommendedItemList = recommender.recommend(1, 3);
//        //打印推荐的结果
//        System.out.println("使用基于用户的协同过滤算法");
//        System.out.println("为用户1推荐3个商品");
//        for (RecommendedItem recommendedItem : recommendedItemList) {
//            System.out.println(recommendedItem);
//        }
//        return null;
    }

    public HashSet<Question> recommendedItems(Integer stuId) throws Exception {
        //创建文件
        String filePath = "./ratings_"+stuId+"_"+new Date().getTime()+".dat";
        File file = new File(filePath);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            //获取所有人的答题数据
            // select * from recommend_answer;
            String sql = "select * from recommend_answer;";
            List<RecommendAnswer> recommendAnswers = jdbcTemplate.query(sql, new BeanPropertyRowMapper<RecommendAnswer>(RecommendAnswer.class));
            //写入数据
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            for (RecommendAnswer recommendAnswer : recommendAnswers) {
                out.write(recommendAnswer.getStuId()+"::"+recommendAnswer.getQuestionId()+"::"+(recommendAnswer.isRight()?1:5)+"::978300760\n");
            }
            out.flush();
            out.close();
            //将数据加载到内存中，GroupLensDataModel是针对开放电影评论数据的
            DataModel dataModel = new GroupLensDataModel(file);
            //计算相似度，相似度算法有很多种，欧几里得、皮尔逊等等。
            UserSimilarity similarity = new EuclideanDistanceSimilarity(dataModel);
            //计算最近邻域，邻居有两种算法，基于固定数量的邻居和基于相似度的邻居，这里使用基于固定数量的邻居
            UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(5, similarity, dataModel);
            //构建推荐器，协同过滤推荐有两种，分别是基于用户的和基于物品的，这里使用基于用户的协同过滤推荐
            Recommender recommender = new GenericUserBasedRecommender(dataModel, userNeighborhood, similarity);
            //给用户ID等于5的用户推荐10部电影
            List<RecommendedItem> recommendedItemList = recommender.recommend(1, 3);
            //打印推荐的结果
            System.out.println("使用基于用户的协同过滤算法");
            String getQuestionSql = "select * from question where id = ?;";
            HashSet<Question> res = new HashSet<>();
            for (RecommendedItem recommendedItem : recommendedItemList) {
                int id = (int) recommendedItem.getItemID();
                System.out.println(recommendedItem);
                Question question = jdbcTemplate.queryForObject(getQuestionSql, new BeanPropertyRowMapper<Question>(Question.class), new Object[]{id});
                question.setCorrectAnswer("");
                res.add(question);
            }
            return res;
        } finally {
            if(file.exists())
                file.delete();
        }
    }

    public HashSet<Question> recommendByTag(int stuId) {
        //获取用户所有tag类型的题目正确率
        //所有tag的题目数
        // select tag_id,name,count(1) as count from tag left join question_tag on tag.id=question_tag.tag_id group by tag_id,name;
        String allTagSql = "select tag_id,count(1) as count from tag left join question_tag on tag.id=question_tag.tag_id group by tag_id,name;";
        List<HashMap<String, Integer>> tagsInfo = jdbcTemplate.query(allTagSql, TagInfoMapper());
        // 拿到用户所有正确的题对应的tag_id,以及question_id
        // select tag_id,question_id from recommend_answer left join question_tag on question_id = quest_id  where stu_id=? and is_right = 1 ;
        String rightQuestionSql = " select tag_id,question_id from recommend_answer left join question_tag on question_id = quest_id  where stu_id=? and is_right = 1 ;";
        List<HashMap<String, Integer>> rightQuestions = jdbcTemplate.query(rightQuestionSql, rightQuestionMapper(), new Object[]{stuId});
        HashSet<Integer> rightQuestionSet = new HashSet<>();
        HashMap<Integer, Integer> rightCnt = new HashMap<>();
        for (HashMap<String, Integer> rightQuestion : rightQuestions) {
            rightQuestionSet.add(rightQuestion.get("questionId"));
            int tagId = rightQuestion.get("tagId");
            if (!rightCnt.containsKey(tagId)) {
                rightCnt.put(tagId, 1);
            } else {
                rightCnt.put(tagId, rightCnt.get(tagId) + 1);
            }
        }
        //按照完成率排序
        ArrayList<CompletedRate> list = new ArrayList<>();
        for (HashMap<String, Integer> tagMap : tagsInfo) {
            System.out.println(tagMap.get("tagId"));
            System.out.println(rightCnt.get(tagMap.get("tagId"))==null?0:rightCnt.get(tagMap.get("tagId")));
            System.out.println(tagMap.get("count"));
            CompletedRate completedRate = new CompletedRate(
                    tagMap.get("tagId"),
                    rightCnt.get(tagMap.get("tagId"))==null?0:rightCnt.get(tagMap.get("tagId")),
                    tagMap.get("count"));
            list.add(completedRate);
        }
        Collections.sort(list);

        //按照tag完成率，for循环 依次从题库中获取题目
        //根据tag_id，去数据库中获取题目，根据question_id，剔除已做过的question    rightQuestionSet
        //拿到tag对应的question_id
        // select * from question_tag left join question on question.id = question_id where tag_id = ?
        HashSet<Question> res = new HashSet<>();
        for (CompletedRate completedRate : list) {
            int tagId = completedRate.getTagId();
            String getQuestionsSql = "select * from question_tag left join question on question.id = quest_id where tag_id = ?;";
            List<Question> questions = jdbcTemplate.query(getQuestionsSql, new BeanPropertyRowMapper<Question>(Question.class), new Object[]{tagId});
            Iterator<Question> iterator = questions.iterator();
            while (iterator.hasNext()) {
                Question next = iterator.next();
                if (rightQuestionSet.contains(next.getId())) {
                    // 删除已经做过的题目
                    iterator.remove();
                } else {
                    String tagsSql = "select * from question_tag left join tag on tag.id=question_tag.tag_id where quest_id = ?;";
                    List<Tag> tags = jdbcTemplate.query(tagsSql, new BeanPropertyRowMapper<Tag>(Tag.class), new Object[]{next.getId()});
                    next.setTags(tags);
                    next.setCorrectAnswer("");
                    res.add(next);
                }
            }
            if (res.size() >= 10) break;
        }
        //返回10题
        return res;
    }

    static class CompletedRate implements Comparable<CompletedRate> {
        private int tagId;
        private int count;
        private int total;
        private double rate;

        public CompletedRate(int tagId, int count, int total) {
            this.tagId = tagId;
            this.count = count;
            this.total = total;
        }

        public int getCount() {
            return count;
        }

        public CompletedRate setCount(int count) {
            this.count = count;
            return this;
        }

        public int getTotal() {
            return total;
        }

        public CompletedRate setTotal(int total) {
            this.total = total;
            return this;
        }

        public int getTagId() {
            return tagId;
        }

        public CompletedRate setTagId(int tagId) {
            this.tagId = tagId;
            return this;
        }

        public double getRate() {
            if (total == 0) {
                return 1;
            }
            return count / total;
        }

        @Override
        public int compareTo(CompletedRate o) {
            return this.getRate() > o.getRate() ? 1 : -1;
        }
    }

    // 1+4*(1-(得分/总分))
//     select a.stu_id as stu_id,b.question_id as question_id,
//        sum(a.score)/sum(b.score)
//      as avg_score from (select asa.ans_number as ans_number,asa.sheet_id as sheet_id,asa.stu_id as stu_id,asa.score as score from answer_sheet_answer asa left join answer_sheet on asa.stu_id=answer_sheet.stu_id and asa.sheet_id=answer_sheet.sheet_id where is_corrected = 1) a
//     inner join sheet_question b
//     on a.ans_number=b.number and a.sheet_id=b.sheet_id
//     where stu_id = 12
//     group by b.question_id;
    public RowMapper<HashMap<String, Integer>> TagInfoMapper() {
        return new RowMapper<HashMap<String, Integer>>() {
            @Override
            public HashMap<String, Integer> mapRow(ResultSet resultSet, int i) throws SQLException {
                HashMap<String, Integer> map = new HashMap<>();
                map.put("tagId", resultSet.getInt("tag_id"));
                map.put("count", resultSet.getInt("count"));
                return map;
            }
        };
    }

    public RowMapper<HashMap<String, Integer>> rightQuestionMapper() {
        return new RowMapper<HashMap<String, Integer>>() {
            @Override
            public HashMap<String, Integer> mapRow(ResultSet resultSet, int i) throws SQLException {
                HashMap<String, Integer> map = new HashMap<>();
                map.put("tagId", resultSet.getInt("tag_id"));
                map.put("questionId", resultSet.getInt("question_id"));
                return map;
            }
        };
    }
}

