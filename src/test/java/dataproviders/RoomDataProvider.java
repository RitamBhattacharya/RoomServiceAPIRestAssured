package dataproviders;

import org.testng.annotations.DataProvider;
import utils.JsonDataLoader;
import utils.JsonMultipleDataLoader;

import java.util.List;
import java.util.Map;

public class RoomDataProvider {

    // DataProvider to supply multiple room datasets from JSON
    @DataProvider(name = "roomData")
    public Object[][] getRoomData() {
        // Load JSON as a list of maps
        List<Map<String, Object>> dataList = JsonMultipleDataLoader.loadJsonAsList("src/test/resources/testdata/addRoom_multiple.json");
        
        // Convert list into Object[][] for TestNG
        Object[][] dataArray = new Object[dataList.size()][1];
        for (int i = 0; i < dataList.size(); i++) {
            dataArray[i][0] = dataList.get(i);
        }
        return dataArray; // Return datasets for parameterized tests
    }
}
