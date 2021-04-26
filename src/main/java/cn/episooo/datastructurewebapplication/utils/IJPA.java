package cn.episooo.datastructurewebapplication.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/21 19:45
 * @Description：
 */
public interface IJPA<T> extends JpaRepository<T,Integer>, JpaSpecificationExecutor<T> {
}
