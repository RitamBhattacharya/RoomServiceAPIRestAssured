package dataproviders;

import org.testng.annotations.DataProvider;
import utils.JsonDataLoader;
import utils.JsonMultipleDataLoader;

import java.util.List;
import java.util.Map;

public class RoomDataProvider {

    @DataProvider(name = "roomData")
    public Object[][] getRoomData() {
        List<Map<String, Object>> dataList = JsonMultipleDataLoader.loadJsonAsList("src/test/resources/testdata/addRoom_multiple.json");
        Object[][] dataArray = new Object[dataList.size()][1];
        for (int i = 0; i < dataList.size(); i++) {
            dataArray[i][0] = dataList.get(i);
        }
        return dataArray;
    }
}
