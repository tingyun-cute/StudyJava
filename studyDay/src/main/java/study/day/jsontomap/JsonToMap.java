package study.day.jsontomap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

public class JsonToMap {

    public static void main(String[] args) {
        // 测试json字符串转Map的方法
        Map<String, Object> result = JSONUtil.parseJSONString2Map("{" +
                        "\"data\": {" +
                        "\"studentList\": [{" +
                        "\"scourse\": {" +
                        "\"cname\": \"语文,数学,英语\"," +
                        "\"cscore\": \"93,95,98\"" +
                        "}," +
                        "\"sname\": \"张三\"," +
                        "\"sage\": \"20\"," +
                        "\"sid\": \"101\"" +
                        "}," +
                        "{" +
                        "\"scourse\": {" +
                        "\"cname\": \"物理,化学,生物\"," +
                        "\"cscore\": \"92,93,97\"" +
                        "}," +
                        "\"sname\": \"李四\"," +
                        "\"sage\": \"30\"," +
                        "\"sid\": \"102\"" +
                        "}]" +
                        "}," +
                        "\"resultCode\": \"1\"" +
                        "}");
        System.out.println(result);

        // 生成测试数据的代码
        List<Map<String, Object>> studentList = new ArrayList<Map<String, Object>>();
        Map<String, Object> student = new HashMap<String, Object>(){{
            put("sid", "101");
            put("sname", "张三");
            put("sage", "20");
            put("scourse", new HashMap<String, String>(){{
                put("cname", "语文,数学,英语");
                put("cscore", "93,95,98");
            }});
        }};
        studentList.add(student);
        student = new HashMap<String, Object>(){{
            put("sid", "102");
            put("sname", "李四");
            put("sage", "30");
            put("scourse", new HashMap<String, String>(){{
                put("cname", "物理,化学,生物");
                put("cscore", "92,93,97");
            }});
        }};
        studentList.add(student);
        Map<String, Object> resultMap = new HashMap<String, Object>(){{
            put("data", new HashMap<String, Object>(){{
                put("studentList", studentList);
            }});
            put("resultCode", "1");
        }};
        System.out.println(JSONObject.fromObject(resultMap).toString());
    }

    public static class JSONUtil{
        /**
         * 将json对象转换为HashMap
         * @param json
         * @return
         */
        public static Map<String, Object> parseJSON2Map(JSONObject json){
            Map<String, Object> map = new HashMap<String, Object>();
            // 最外层解析
            for (Object k : json.keySet()){
                Object v = json.get(k);
                // 如果内层还是json数组的话，继续解析
                if (v instanceof JSONArray){
                    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                    Iterator<JSONObject> it = ((JSONArray) v).iterator();
                    while (it.hasNext()){
                        JSONObject json2 = it.next();
                        list.add(parseJSON2Map(json2));
                        map.put(k.toString(), list);
                    }
                }else if (v instanceof JSONObject){
                    // 如果内层是json对象的话，继续解析
                    map.put(k.toString(), parseJSON2Map((JSONObject) v));
                }else {
                    // 如果是普通对象的话，直接放入map中
                    map.put(k.toString(), v);
                }
            }
            return map;
        }

        /**
         * 将json字符串转换为Map
         * @param jsonStr
         * @return
         */
        public static Map<String, Object> parseJSONString2Map(String jsonStr){
            JSONObject json = JSONObject.fromObject(jsonStr);
            Map<String, Object> map = parseJSON2Map(json);
            return map;
        }
    }
}
