import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author youguoqiang
 * @date 2020/8/3
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AreaDTO {
    private String areaCode;
    private String province;
    private String city;
    private String area;
    private String year;
    private String end;
    private String flag;

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof AreaDTO)){
            return false;
        }
        AreaDTO obj1 = (AreaDTO) obj;
        if(obj1.area.equals(area) && obj1.areaCode.equals(areaCode) && obj1.province.equals(province)
                && obj1.city.equals(city)){
            return true;
        }
        return false;
    }
}
