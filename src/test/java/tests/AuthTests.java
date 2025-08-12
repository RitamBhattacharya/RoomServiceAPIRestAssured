package tests;

import base.TestBase;
import org.testng.annotations.Test;
import utils.TokenManager;
import static org.testng.Assert.*;

public class AuthTests extends TestBase {
    
    // Test: Verify authentication flow (Auth Code → Access Token)
    @Test
    public void testAuthFlow() {
        // Get authentication code
        String authCode = TokenManager.getAuthCode();
        getTest().info("Auth code: " + authCode);
        assertNotNull(authCode); // Ensure auth code is not null
        
        // Get access token using auth code
        String token = TokenManager.getAccessToken();
        getTest().info("Access token: " + token);
        assertNotNull(token); // Ensure token is not null
    }
}
