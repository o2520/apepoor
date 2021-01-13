import lombok.Data;

import java.math.BigDecimal;

/**
 * @author youguoqiang
 * @date 2020/8/19
 */
@Data
public class Product2DTO {
    private String key;
    private String function;
    private String month;
    private String day;
    private String source;
    private BigDecimal times;

}
