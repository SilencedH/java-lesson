package net.youzule.spring.chapter03.app04;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @description:
 * @company:
 * @author:Sean
 * @date:2018/7/4 13:51
 **/

public class CollectionsBean {
    private List<String> list;
    private Set<String> set;
    private Map<String,String> map;

    private Properties properties;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "CollectionsBean{" +
                "list=" + list +
                ", set=" + set +
                ", map=" + map +
                ", properties=" + properties +
                '}';
    }
}
