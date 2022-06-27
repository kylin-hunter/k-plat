import java.io.File;

import org.jodconverter.core.office.OfficeException;
import org.jodconverter.local.JodConverter;
import org.jodconverter.local.office.LocalOfficeManager;

import com.kylinhunter.plat.commons.io.ResourceHelper;
import com.kylinhunter.plat.commons.io.file.UserDirUtils;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-24 22:43
 **/
public class OpenOfficeTest {

    public static void main(String[] args) {
        try {
            //据说jodconverter可以自动寻找转码路径，windows上试了可以，linux上好像没法自动找到路径，会报officehome not set and could not be
            // auto-detected，所以设置转码工具路径
            LocalOfficeManager manager =
                    LocalOfficeManager.builder().install().build();
            manager.start();
            final File source = ResourceHelper.getFileInClassPath("test.docx");
            final File dist = UserDirUtils.getTmpFile("test.pdf");
            JodConverter.convert(source).to(dist).execute();

            System.out.println("convert ok " + dist.getAbsolutePath());
        } catch (OfficeException e) {
            e.printStackTrace();
        }
    }
}
