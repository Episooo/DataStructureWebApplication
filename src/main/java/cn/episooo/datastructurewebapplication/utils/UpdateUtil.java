package cn.episooo.datastructurewebapplication.utils;

import cn.episooo.datastructurewebapplication.domain.ID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author ：Ep
 * @Date ：Created in 2020/11/13 16:43
 * @Description：
 */
public class UpdateUtil {
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue instanceof List){
                System.out.println("00000000000000");
                System.out.println(pd.getName());
                if (((List)srcValue).size()==0){
                    srcValue=null;
                }
            }
            if (srcValue == null) {
                emptyNames.add(pd.getName());
                System.out.println(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static <T extends ID> void updateById(JpaRepository<T,Integer> dao, T newObj){
        T oldValue = dao.findById(newObj.getId()).get();
        System.out.println(oldValue);

        BeanUtils.copyProperties(newObj,oldValue, UpdateUtil.getNullPropertyNames(newObj));

        System.out.println("UpdateUtil:"+ newObj);
        dao.save(oldValue);
    }
}