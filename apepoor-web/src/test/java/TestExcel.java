import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

import java.util.*;

/**
 * @author youguoqiang
 * @date 2020/8/3
 */
public class TestExcel {


    public void test(){
        List<AreaDTO> list = new ArrayList<>();
        Map<String, AreaDTO> areaCodeMap = new HashMap<>();
        ExcelUtil.read03BySax(FileUtil.file("/Users/youguoqiang/Downloads/area1.xls"), 0, (sheetIndex, rowIndex, rowlist) -> {
            if(rowIndex==0){
                return;
            }
            AreaDTO areaDTO = AreaDTO.builder().areaCode(rowlist.get(0).toString())
                    .province(rowlist.get(1).toString())
                    .city(rowlist.get(2).toString())
                    .area(rowlist.get(3).toString())
                    .year(rowlist.get(4).toString()).build();

            if(areaCodeMap.containsKey(areaDTO.getAreaCode())){
                AreaDTO dto = areaCodeMap.get(areaDTO.getAreaCode());
                if(dto.equals(areaDTO)){
                    return;
                }
                areaCodeMap.put(areaDTO.getAreaCode(), areaDTO);
                dto.setEnd(Integer.valueOf(areaDTO.getYear())-1 + "-12-31");
                list.add(dto);
                return;
            }
            areaCodeMap.put(areaDTO.getAreaCode(), areaDTO);
        });
        ExcelUtil.read03BySax(FileUtil.file("/Users/youguoqiang/Downloads/area1.xls"), 1, (sheetIndex, rowIndex, rowlist) -> {
            if(rowIndex==0){
                return;
            }
            AreaDTO areaDTO = AreaDTO.builder().areaCode(rowlist.get(0).toString())
                    .province(rowlist.get(1).toString())
                    .city(rowlist.get(2).toString())
                    .area(rowlist.get(3).toString())
                    .year(rowlist.get(4).toString()).build();

            if(areaCodeMap.containsKey(areaDTO.getAreaCode())){
                AreaDTO dto = areaCodeMap.get(areaDTO.getAreaCode());
                if(dto.equals(areaDTO)){
                    return;
                }
                areaCodeMap.put(areaDTO.getAreaCode(), areaDTO);
                dto.setEnd(Integer.valueOf(areaDTO.getYear())-1 + "-12-31");
                list.add(dto);
                return;
            }
            areaCodeMap.put(areaDTO.getAreaCode(), areaDTO);
        });
        System.out.println(areaCodeMap.size());
        System.out.println(list.size());
        ExcelWriter writer = ExcelUtil.getWriter("/Users/youguoqiang/Downloads/area2.xls");
        writer.write(list, true);
        writer.write(areaCodeMap.values(), true);
        writer.close();
    }

    public static void main(String[] args) {

        List<AreaDTO> list = new ArrayList<>();
        Map<String, AreaDTO> areaCodeMap = new HashMap<>();
        ExcelUtil.read03BySax(FileUtil.file("/Users/youguoqiang/Downloads/area1.xls"), 0, (sheetIndex, rowIndex, rowlist) -> {
            AreaDTO areaDTO = AreaDTO.builder().areaCode(rowlist.get(0).toString())
                    .province(rowlist.get(1).toString())
                    .city(rowlist.get(2).toString())
                    .area(rowlist.get(3).toString())
                    .year(rowlist.get(4).toString()).build();

            if(areaCodeMap.containsKey(areaDTO.getAreaCode())){
                AreaDTO dto = areaCodeMap.get(areaDTO.getAreaCode());
                if(dto.equals(areaDTO)){
                    return;
                }
                areaCodeMap.put(areaDTO.getAreaCode(), areaDTO);
                dto.setEnd(Integer.valueOf(areaDTO.getYear())-1 + "-12-31");
                list.add(dto);
                return;
            }
            areaCodeMap.put(areaDTO.getAreaCode(), areaDTO);
        });
        ExcelUtil.read03BySax(FileUtil.file("/Users/youguoqiang/Downloads/area1.xls"), 1, (sheetIndex, rowIndex, rowlist) -> {
            if(rowIndex==0){
                return;
            }
            AreaDTO areaDTO = AreaDTO.builder().areaCode(rowlist.get(0).toString())
                    .province(rowlist.get(1).toString())
                    .city(rowlist.get(2).toString())
                    .area(rowlist.get(3).toString())
                    .year(rowlist.get(4).toString()).build();

            if(areaCodeMap.containsKey(areaDTO.getAreaCode())){
                AreaDTO dto = areaCodeMap.get(areaDTO.getAreaCode());
                if(dto.equals(areaDTO)){
                    return;
                }
                areaCodeMap.put(areaDTO.getAreaCode(), areaDTO);
                dto.setEnd(Integer.valueOf(areaDTO.getYear())-1 + "-12-31");
                list.add(dto);
                return;
            }
            areaCodeMap.put(areaDTO.getAreaCode(), areaDTO);
        });
        System.out.println(areaCodeMap.size());
        System.out.println(list.size());


        List<AreaDTO> list2 = new ArrayList<>();
        SortedMap<String, Map<String, AreaDTO>> map = new TreeMap<>(Comparator.reverseOrder());
        ExcelReader reader = ExcelUtil.getReader("/Users/youguoqiang/Downloads/area1.xls");
        List<AreaDTO> all = reader.readAll(AreaDTO.class);
        all.stream().forEach(areaDTO -> {
            Map<String, AreaDTO> areaDTOMap = map.get(areaDTO.getYear());
            if(areaDTOMap == null){
                areaDTOMap = new HashMap<>();
            }
            areaDTOMap.put(areaDTO.getAreaCode(), areaDTO);
            map.put(areaDTO.getYear(), areaDTOMap);
        });

        for(String key : map.keySet()){
            if("1980".equals(key)){
                continue;
            }
            for(Map.Entry<String, AreaDTO> entry : map.get(key).entrySet()){
                Map<String, AreaDTO> stringAreaDTOMap = map.get(String.valueOf(Integer.valueOf(key) - 1));
                if(!stringAreaDTOMap.containsKey(entry.getKey())){
                    AreaDTO value = entry.getValue();
                    value.setEnd(key + "-12-31");
                    list2.add(value);
                }
            }
        }

        /*ExcelReader reader2 = ExcelUtil.getReader("/Users/youguoqiang/Downloads/area5.xlsx");
        List<AreaDTO> all2 = reader2.readAll(AreaDTO.class);
        all2.stream().forEach(areaDTO -> {
            Map<String, AreaDTO> areaDTOMap = map.get(areaDTO.getYear());
            if(areaDTOMap == null){
                areaDTOMap = new HashMap<>();
            }
            areaDTOMap.put(areaDTO.getAreaCode(), areaDTO);
            map.put(areaDTO.getYear(), areaDTOMap);
        });

        for(String key : map.keySet()){
            if("1980".equals(key)){
                continue;
            }
            for(Map.Entry<String, AreaDTO> entry : map.get(key).entrySet()){
                Map<String, AreaDTO> stringAreaDTOMap = map.get(String.valueOf(Integer.valueOf(key) - 1));
                if(!stringAreaDTOMap.containsKey(entry.getKey())){
                    AreaDTO value = entry.getValue();
                    value.setEnd(key + "-12-31");
                    list.add(value);
                }
            }
        }*/

        list2.stream().forEach(areaDTO -> {
            areaCodeMap.put(areaDTO.getAreaCode(), areaDTO);
        });

        list.addAll(areaCodeMap.values());


        System.out.println(list.size());
        System.out.println(areaCodeMap.size());
//        ExcelWriter writer = ExcelUtil.getWriter("/Users/youguoqiang/Downloads/area3.xlsx");
//        writer.write(list, true);
//        writer.close();
    }
}
