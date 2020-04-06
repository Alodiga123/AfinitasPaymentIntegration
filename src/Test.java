
import com.google.gson.Gson;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ltoro
 */
public class Test {
    
    
 public static void main(String... args) throws Exception {
        String json = "{\"access_token\":\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2VudGl0eSI6NDMsImF1ZCI6WyJyZXNvdXJjZS1zZXJ2ZXItcmVzdC1hcGkiXSwidXNlcl9pZCI6MTMwLCJ1c2VyX25hbWUiOiJqY2FsZGVyYXNAYWxvZGlnYS5jb20iLCJ1c2VyX3N5c3RlbSI6Niwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImV4cCI6MTU4NTI0MjQzNCwiYnVzaW5lc3NfaWQiOjQzLCJhdXRob3JpdGllcyI6WyJDQU5DRUxBQ0nDk04gT05MSU5FIiwiUkVWRVJTTyBPTkxJTkUiLCJERVZPTFVDScOTTiBPTkxJTkUiLCJWRU5UQSAgT05MSU5FIl0sImp0aSI6ImY1ODM5NGM5LTlhYWUtNDU0Ni1hMzQ1LWZjMjUxOGExMGNjNSIsImNsaWVudF9pZCI6ImJsdW1vbl9wYXlfZWNvbW1lcmNlX2FwaSJ9.8jou71ZqIeGAIB6ywjyfMeaQDpuKfyJ58To4SFqH5Q4\",\"token_type\":\"bearer\",\"refresh_token\":\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2VudGl0eSI6NDMsImF1ZCI6WyJyZXNvdXJjZS1zZXJ2ZXItcmVzdC1hcGkiXSwidXNlcl9pZCI6MTMwLCJ1c2VyX25hbWUiOiJqY2FsZGVyYXNAYWxvZGlnYS5jb20iLCJ1c2VyX3N5c3RlbSI6Niwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImF0aSI6ImY1ODM5NGM5LTlhYWUtNDU0Ni1hMzQ1LWZjMjUxOGExMGNjNSIsImV4cCI6MTU4NzgyMzYzNCwiYnVzaW5lc3NfaWQiOjQzLCJhdXRob3JpdGllcyI6WyJDQU5DRUxBQ0nDk04gT05MSU5FIiwiUkVWRVJTTyBPTkxJTkUiLCJERVZPTFVDScOTTiBPTkxJTkUiLCJWRU5UQSAgT05MSU5FIl0sImp0aSI6IjczM2E0MzkyLWE1MDMtNDM0OS1hYjRhLWRjNGJjZmFiZmU5MCIsImNsaWVudF9pZCI6ImJsdW1vbl9wYXlfZWNvbW1lcmNlX2FwaSJ9.zFW7F2gtgoowI_GbJVrw4tiiXr2vcZSzEfmZjhcwc5c\",\"expires_in\":10799,\"scope\":\"read write\",\"user_entity\":43,\"user_id\":130,\"user_system\":6,\"business_id\":43,\"jti\":\"f58394c9-9aae-4546-a345-fc2518a10cc5\"}";
            

        // Now do the magic.
      //  Data data = new Gson().fromJson(json, Data.class);

        // Show it.
         Data data = new Gson().fromJson(json, Data.class);
        
        System.out.println(data);
    }

 
 class Data {
    private String access_token;
    

    public String toString() {
        return String.format("access_token:%s", access_token);
    }
 
}
 
}


