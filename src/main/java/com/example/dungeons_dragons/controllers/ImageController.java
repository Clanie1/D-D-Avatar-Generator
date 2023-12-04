package com.example.dungeons_dragons.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ImageController {

    @PostMapping(value="/image")
    public ResponseEntity<ImageGenerationResponse> getImage(@RequestBody MyRequestData requestBody) {
        try{
            URL url = new URL("https://api.openai.com/v1/images/generations");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer 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");
            // String data = "{\n" +
            //               "    \"model\": \"dall-e-3\",\n" +
            //               "    \"prompt\": \"You are a Dungeons and Dragons Avatar Generator. You want to generate images that can be used inside a game. The avatar needs to have the following attributes. Name: "+ requestBody.getNombre()+". Age: "+ requestBody.getEdad()+". Gender: "+ requestBody.getGenero()+". Class: "+ requestBody.getClase()+". Description: "+requestBody.getDescription()+". Use your imagination and remember to be similar to Dungeons and Dragons style\",\n" +
            //               "    \"n\": 1,\n" +
            //               "    \"size\": \"1024x1024\"\n" +
            //               "}";
            String data = "{\n" +
                          "    \"model\": \"dall-e-3\",\n" +
                          "    \"prompt\": \"You are a Dungeons and Dragons Escene Generator. You want to generate images that can be used as escenes inside a game, make them as epic as possible with engaging backgrounds. The avatar needs to have the following attributes. Name: "+ requestBody.getNombre()+". Age: "+ requestBody.getEdad()+". Gender: "+ requestBody.getGenero()+". Class: "+ requestBody.getClase()+". Description: "+requestBody.getDescription()+".\",\n" +
                          "    \"n\": 1,\n" +
                          "    \"size\": \"1024x1024\"\n" +
                          "}";
        
            con.setDoOutput(true);
            con.getOutputStream().write(data.getBytes("UTF-8"));
        
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
              response.append(inputLine);
            }
            in.close();
            String responseString = response.toString();
            ObjectMapper objectMapper = new ObjectMapper();
            ImageGenerationResponse imageGenerationResponse = objectMapper.readValue(responseString, ImageGenerationResponse.class);
 
            return ResponseEntity.ok(imageGenerationResponse);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}

class MyRequestData {
    private String nombre;
    private String edad;
    private String genero;
    private String clase;
    private String description;
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEdad() {
        return edad;
    }
    public void setEdad(String edad) {
        this.edad = edad;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getClase() {
        return clase;
    }
    public void setClase(String clase) {
        this.clase = clase;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}

class ImageGenerationResponse {
    @JsonProperty("created")
    private long created;

    @JsonProperty("data")
    private List<ImageData> data;

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public List<ImageData> getData() {
        return data;
    }

    public void setData(List<ImageData> data) {
        this.data = data;
    }

    // getters and setters
}

class ImageData {
    @JsonProperty("revised_prompt")
    private String revisedPrompt;

    @JsonProperty("url")
    private String url;

    public String getRevisedPrompt() {
        return revisedPrompt;
    }

    public void setRevisedPrompt(String revisedPrompt) {
        this.revisedPrompt = revisedPrompt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // getters and setters
}


