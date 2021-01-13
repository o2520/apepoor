import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author youguoqiang
 * @date 2020/8/3
 */
public class TestExcel4 {

    public static void main(String[] args) {
        ExcelReader reader0 = ExcelUtil.getReader("/Users/youguoqiang/Downloads/11.xlsx");
        reader0.setSheet(3);
        List<Product2DTO> list = reader0.readAll(Product2DTO.class);
        Iterator<Product2DTO> iterator = list.iterator();
        System.out.println(list.size());
        while (iterator.hasNext()){
            Product2DTO next = iterator.next();
            if(StringUtils.isEmpty(next.getKey())){
                iterator.remove();
            }
        }
        System.out.println(list.size());

        ExcelReader reader1 = ExcelUtil.getReader("/Users/youguoqiang/Downloads/11.xlsx");
        reader1.setSheet(4);
        List<ProductDTO> resultList = reader1.readAll(ProductDTO.class);
        AtomicInteger count = new AtomicInteger(0);
        resultList.parallelStream().forEach(productDTO -> {
            Optional<Product2DTO> any = list.stream().filter(product2DTO -> product2DTO.getFunction().equalsIgnoreCase(productDTO.getFunction()) && productDTO.getName().indexOf(product2DTO.getKey()) > -1).findAny();
            any.ifPresent(product2DTO -> {
                productDTO.setMonth(product2DTO.getMonth());
                productDTO.setDay(product2DTO.getDay());
                count.getAndIncrement();
            });
        });
        System.out.println(resultList.size());
        System.out.println(count.get());
        ExcelWriter writer = ExcelUtil.getWriter("/Users/youguoqiang/Downloads/11.xlsx");
        writer.setSheet(5);
        writer.write(resultList, true);
        writer.close();
        /*List<AreaDTO> all1 = reader1.readAll(AreaDTO.class);
        all0.addAll(all1);

        SortedMap<String, Map<String, AreaDTO>> mapByYearDesc = new TreeMap<>(Comparator.reverseOrder());
        Map<String, AreaDTO> allMap = new HashMap<>();
        all0.stream().forEach(areaDTO -> {
            if("year".equals(areaDTO.getYear())){
                return;
            }
            Map<String, AreaDTO> mapByCode = mapByYearDesc.get(areaDTO.getYear());
            if(mapByCode == null){
                mapByCode = new HashMap<>();
            }
            mapByCode.put(areaDTO.getAreaCode(), areaDTO);
            mapByYearDesc.put(areaDTO.getYear(), mapByCode);
            String key = areaDTO.getAreaCode() + ":" + areaDTO.getProvince() + ":" + areaDTO.getCity() + ":" + areaDTO.getArea();
            if(allMap.containsKey(key)){
                AreaDTO areaDTO1 = allMap.get(key);
                areaDTO1.setEnd(areaDTO.getYear()+"-12-31");
                allMap.put(areaDTO.getAreaCode()+":"+areaDTO.getProvince()+":"+areaDTO.getCity()+":"+areaDTO.getArea(), areaDTO1);
            }else {
                allMap.put(key, areaDTO);
            }
        });

        SortedMap<String, Map<String, AreaDTO>> mapByYear = new TreeMap<>();
        mapByYear.putAll(mapByYearDesc);
        allMap.values().stream().filter(areaDTO -> areaDTO.getEnd() == null).forEach(areaDTO -> {
            for(String key : mapByYear.keySet()){
                if(Integer.valueOf(key).compareTo(Integer.valueOf(areaDTO.getYear())) <= 0){
                    continue;
                }
                AreaDTO areaDTO1 = mapByYear.get(key).get(areaDTO.getAreaCode());
                if(null == areaDTO1 || !areaDTO1.equals(areaDTO)){
                    areaDTO.setEnd(Integer.valueOf(key)-1 + "-12-31");
                    break;
                }
            }
        });*/


        /*Iterator<String> yearIterator = mapByYearDesc.keySet().iterator();
        while (yearIterator.hasNext()){
            String year = yearIterator.next();
            if(){

            }
        }*/

        /*List<AreaDTO> total = new ArrayList<>(2048);
        for(String key : mapByYearDesc.keySet()){
            if("1980".equals(key)){
                continue;
            }

            for(Map.Entry<String, AreaDTO> entry : mapByYearDesc.get(key).entrySet()){
                String lastYear = String.valueOf(Integer.valueOf(key) - 1);
                Map<String, AreaDTO> mapByLastYear = mapByYearDesc.get(lastYear);
                AreaDTO value = entry.getValue();
                if(mapByLastYear.containsKey(entry.getKey())){
                    AreaDTO areaDTO = mapByLastYear.get(entry.getKey());
                    if(!areaDTO.equals(entry.getValue())){
                        if(total.contains(value)){
                            continue;
                        }
                        areaDTO.setEnd(lastYear+"-12-31");
                        areaDTO.setFlag("0");
                        total.add(areaDTO);
                        value.setFlag("1");
                        total.add(value);
                    }else {
                        value.setFlag("2");
                        value.setEnd(key+"-12-31");
                    }
                }else {
                    if(total.contains(value)){
                        continue;
                    }
                    value.setFlag("3");
                    total.add(value);
                }
            }
        }
        System.out.println(total.size());

        SortedMap<String, Map<String, AreaDTO>> mapByYear = new TreeMap<>();
        mapByYear.putAll(mapByYearDesc);

        for(String key : mapByYear.keySet()){
            if("2020".equals(key)){
                continue;
            }

            for(Map.Entry<String, AreaDTO> entry : mapByYear.get(key).entrySet()){
                Map<String, AreaDTO> mapByNextYear = mapByYear.get(String.valueOf(Integer.valueOf(key) + 1));
                AreaDTO value = entry.getValue();
                if(!mapByNextYear.containsKey(entry.getKey())){
                    if(total.contains(value)){
                        continue;
                    }
                    value.setEnd(key + "-12-31");
                    total.add(value);
                    value.setFlag("4");
                }
            }
        }*/
//        System.out.println(allMap.size());

//        List<AreaDTO> collect = fooMap.values().stream().filter(areaDTO -> !"0".equals(areaDTO.getFlag())).collect(Collectors.toList());
//        System.out.println(collect.size());
//        total.addAll(collect);
//        ExcelWriter writer = ExcelUtil.getWriter("/Users/youguoqiang/Downloads/area3.xlsx");
//        writer.setSheet(2);
//        writer.write(allMap.values(), true);
//        writer.close();
    }

}
