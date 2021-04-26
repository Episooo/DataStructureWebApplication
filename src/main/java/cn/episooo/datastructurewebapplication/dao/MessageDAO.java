package cn.episooo.datastructurewebapplication.dao;

import cn.episooo.datastructurewebapplication.domain.Message;
import cn.episooo.datastructurewebapplication.utils.IJPA;

import java.util.List;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/20 20:31
 * @Description：
 */
public interface MessageDAO extends IJPA<Message> {
    public List<Message> findAllByFromIdAndAndIsReadOrderByIdAsc(int fromId, boolean read);//找到某个人的未读消息
    public List<Message> findAllByToIdAndAndIsReadOrderByIdAsc(int toId,boolean read);
    public List<Message> findTop10ByFromIdOrToIdOrderByIdDesc(int id1, int id2);//找10条最新消息，倒序
//    public List<Message> findTop10ByFromIdInOrToIdInOrderByIdDesc(List<Integer> fromIds,List<Integer> toIds);//找10条最新消息，倒序
    public List<Message> findTop10ByIdLessThanAndFromIdOrIdLessThanAndToIdOrderByIdDesc(int msgId1,int id1,int msgId2,int id2);
//    public List<Message> findTop10ByIdLessThanAndFromIdInOrIdLessThanAndToIdInOrderByIdDesc(int id1,List<Integer> fromIds,int id2,List<Integer> toIds);//找10条历史消息，倒叙
    public List<Message> findAllByIdAfterAndFromIdOrIdAfterAndToId(int msgId1,int id1,int msgId2,int id2);
//    public List<Message> findAllByIdAfterAndFromIdInOrIdAfterAndToIdIn(int id1, List<Integer> fromIds, int id2, List<Integer> toIds);//找到所有新消息
    public List<Message> findAllByToIdAndIsRead(int toId,boolean read);//找到自己所有未读消息
}
