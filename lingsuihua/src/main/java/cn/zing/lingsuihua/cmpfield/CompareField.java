package cn.zing.lingsuihua.cmpfield;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * @description: 两个java bean属性差异对比
 * @author: dcy
 * @create: 2023-08-17 17:03
 */
public class CompareField {

    /**
     * @param obj1      进行属性比较的对象1
     * @param obj2      进行属性比较的对象2
     * @param ignoreArr 选择忽略比较的属性数组
     * @return 属性差异比较结果 map
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, List<Object>> compareFields(Object obj1, Object obj2, String[] ignoreArr) {
        try {
            Map<String, List<Object>> map = new HashMap<String, List<Object>>();
            List<String> ignoreList = null;
            if (ignoreArr != null && ignoreArr.length > 0) {
                ignoreList = Arrays.asList(ignoreArr);
            }
            if (obj1.getClass() == obj2.getClass()) { //只有两个对象都是同一类型才有可比性
                Class clazz = obj1.getClass();
                //获取object的属性描述
                PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz, Object.class).getPropertyDescriptors();
                for (PropertyDescriptor pd : pds) {
                    String name = pd.getName();
                    if (ignoreList != null && ignoreList.contains(name)) {
                        continue;
                    }
                    Method readMethod = pd.getReadMethod();//获取属性的get方法
                    //在obj1上调用get方法等同于获得obj1的属性值
                    Object o1 = readMethod.invoke(obj1);
                    //在obj2上调用get方法等同于获得obj2的属性值
                    Object o2 = readMethod.invoke(obj2);
                    //如果此属性是时间
                    if (o1 instanceof Timestamp) {
                        o1 = new Date(((Timestamp) o1).getTime());
                    }
                    if (o2 instanceof Timestamp) {
                        o2 = new Date(((Timestamp) o2).getTime());
                    }
                    if (o1 == null && o2 == null) {
                        continue;
                    } else if (o1 == null && o2 != null) {
                        //不一致
                        List<Object> list = new ArrayList<Object>();
                        list.add(o1);
                        list.add(o2);
                        map.put(name, list);
                        continue;
                    }
                    if (!o1.equals(o2)) {
                        List<Object> list = new ArrayList<>();
                        list.add(o1);
                        list.add(o2);
                        map.put(name, list);
                    }
                }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Fruit fruit1 = new Fruit();
        fruit1.setFName("苹果");
        BigDecimal b = new BigDecimal("20");
        fruit1.setFPrice(b);
        fruit1.setFType("富士");

        Fruit fruit2 = new Fruit();
        fruit2.setFName("苹果");
        b = new BigDecimal("21");
        fruit2.setFPrice(b);
        fruit2.setFType("富士");

        System.out.println(compareFields(fruit1, fruit2, null));
    }
}


