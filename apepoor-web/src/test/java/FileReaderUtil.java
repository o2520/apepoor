import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;

import java.util.List;

/**
 * @author youguoqiang
 * @date 2020/8/18
 */
public class FileReaderUtil {

    public static void main(String[] args) {
        FileReader reader = FileReader.create(FileUtil.file(""));
        List<String> list = reader.readLines();
        list.stream().forEach(s -> {

        });
    }
}
