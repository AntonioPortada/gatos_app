/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jorge.gatos_app;

import com.google.gson.Gson;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author antonioportada
 */
public class GatoService {

    public static void verGatos() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("").get().build();
        Response response = client.newCall(request).execute();
        String responseJson = response.body().toString();
        //elimina corchetes para manejarlo como objeto
        responseJson = responseJson.substring(1, responseJson.length());
        responseJson = responseJson.substring(0, responseJson.length() - 1);
        //mapea entity
        Gson gson = new Gson();
        Gato gato = gson.fromJson(responseJson, Gato.class);
        //redimensionar imágen si se necesita
        Image image = null;
        try {
            URL url = new URL(gato.getImage());
            image = ImageIO.read(url);
            //es para el JOptionPane
            ImageIcon fondoGato = new ImageIcon(image);

            //valida el tamaño para redimensionar
            if (fondoGato.getIconWidth() > 800) {
                Image fondo = fondoGato.getImage();
                Image modificada = fondo.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
                fondoGato = new ImageIcon(modificada);
            }

            String menu = "Opciones: \n"
                    + "1.- Ver otra imágen\n"
                    + "2.- Favorito\n"
                    + "3.- Volver\n";
            String[] botones = {"Ver otra imágen", "Favorito", "Volver"};
            String idGato = gato.getId();
            String opcion = (String) JOptionPane.showInputDialog(null, menu, idGato, JOptionPane.INFORMATION_MESSAGE, fondoGato, botones, botones[0]);
            int seleccion = -1;

            for (int i = 0; i < botones.length; i++) {
                if (opcion.equals(botones[i])) {
                    seleccion = i;
                }
            }

            switch (seleccion) {
                case 0 ->
                    verGatos();
                case 1 ->
                    favoritoGato(gato);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static void favoritoGato(Gato gato) {

    }

    public static void verFavoritos() throws IOException {
        Gato g = new Gato();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("")
                .get()
                .addHeader("Content-Type", "application/json")
                .addHeader("x-api-key", g.getApiKey())
                .build();
        Response response = client.newCall(request).execute();
        Gson gson = new Gson();
        String favString = response.body().toString();
        GatosFav[] fav = gson.fromJson(favString, GatosFav[].class);

        if (fav.length > 0) {
            int min = 1;
            int max = fav.length;
            int random = (int) (Math.random() * ((max - min) + 1)) + min;
            int indice = random - 1;
            GatosFav gatoFav = fav[indice];
            Image image = null;
            try {
                URL url = new URL(gatoFav.getImag().getUrl());
                image = ImageIO.read(url);
                //es para el JOptionPane
                ImageIcon fondoGato = new ImageIcon(image);

                //valida el tamaño para redimensionar
                if (fondoGato.getIconWidth() > 800) {
                    Image fondo = fondoGato.getImage();
                    Image modificada = fondo.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
                    fondoGato = new ImageIcon(modificada);
                }

                String menu = "Opciones: \n"
                        + "1.- Ver otra imágen\n"
                        + "2.- Eliminar favorito\n"
                        + "3.- Volver\n";
                String[] botones = {"Ver otra imágen", "Eliminar favorito", "Volver"};
                String idGato = gatoFav.getId();
                String opcion = (String) JOptionPane.showInputDialog(null, menu, idGato, JOptionPane.INFORMATION_MESSAGE, fondoGato, botones, botones[0]);
                int seleccion = -1;

                for (int i = 0; i < botones.length; i++) {
                    if (opcion.equals(botones[i])) {
                        seleccion = i;
                    }
                }

                switch (seleccion) {
                    case 0 ->
                        verGatos();
                    case 1 ->
                        borrarFavorito(gatoFav);
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
    
    public static void borrarFavorito(GatosFav gatoFav) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("")
                    .delete()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", gatoFav.getApikey())
                    .build();
            Response response = client.newCall(request).execute();
            
        } catch(IOException ex) {
            System.out.println(ex);
        }
    }
}
