import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Collections;
/**
 * A class to hold details of audio tracks.
 * Individual tracks may be played.
 * 
 * @author David J. Barnes and Michael KÃ¶lling
 * @version 2011.07.31
 */
public class MusicOrganizer
{
    // An ArrayList for storing music tracks.
    private ArrayList<Track> tracks;
    // A player for the music tracks.
    private MusicPlayer player;
    // A reader that can read music files and load them as tracks.
    private TrackReader reader;
   // atributo para indicar reproducciones en curso
   private boolean playMusic;
   //random
   private int alea;
    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer()
    {
        tracks = new ArrayList<Track>();
        player = new MusicPlayer();
        reader = new TrackReader();
        readLibrary("audio");
        readLibrary("miMusica"); // le una carpeta , las canciones en ella 
        playMusic = false;
        System.out.println("Music library loaded. " + getNumberOfTracks() + " tracks.");
        System.out.println();
        
    }
    
   /**
    * metodo para listAllTrackWithIterator que muestre los detalles de todos los tracks 
    * almacenados en un organizador usando un iterador.
    */
   public void listAllTrackWithIterator ()
   {
       Iterator<Track> it = tracks.iterator();
       while(it.hasNext()){
           Track file = it.next();
           System.out.println(file.getDetails());
      
        }
    }
       
    /**
     * método llamado removeByArtist que permita eliminar del organizador 
     * tracks que contengan un determinado artista usando un iterador.
     */
    public void removeByArtist(String artist)
    {
       Iterator<Track> it = tracks.iterator();
       while(it.hasNext()){
           
           Track file = it.next();
           if(file.getArtist().contains(artist))
           {
             it.remove();
           }
           
      
        }
    }
    
    /**
     * Implementa en la clase MusicOrganizer un método llamado removeByTitle
     * que permita eliminar del organizador tracks que contengan
     * una determinada cadena en el título de la canción usando un iterador.
     */
     public void removeByTitle(String title)
    {
       
       Iterator<Track> it = tracks.iterator();
       while(it.hasNext()){
           
           Track file = it.next();
           if(file.getTitle().contains(title))
           {
             it.remove();
           }
           
      
        }
    }
   
    /**
     * Implementa un método llamado playRandom que reproduzca una 
     * de las canciones del organizador al azar. Investiga la clase 
     * Random del paquete java.util y su método nextInt para cumplir tu cometido.
     */
    public void playRandom()
    {
       // alea= (int) (Math.random()*tracks.size() + 0); //genera num aleatorio
       //http://docs.oracle.com/javase/7/docs/api/java/util/Random.html#nextInt%28int%29
       Random  rnd = new Random();
       int index = 0;
       index = (int)  rnd.nextInt(tracks.size());
       
       playTrack(index);
               
       }
   
       /**
        * Implementa un método llamado playShuffle 
        * que permita reproducir los primeros segundos de cada canción en orden aleatorio
        * y que cumpla los siguientes requisitos:
        * - Cada canción debe reproducirse una única vez y deben reproducirse todas.
        * - Los contadores de reproducción deben actualizarse correctamente.
        * - Debe mostrar por pantalla los detalles de la canción que está sonando en este momento.
        * - La forma de solucionarlo debe basarse en el uso del método shuffle de los ArrayList 
        * que deberás investigar en Internet.
        */
    public void playShuffle()
    {
      Collections.shuffle(tracks); //desordena la lista de canciones 
       int index =0; // contador a cero
       while(index < tracks.size())//ejecucion mientras que contador menor que num elementos
       {
           Track music = tracks.get(index);//Se guarda  la cancion  con el indice
           System.out.println(music.getDetails());//Muestra los detalles del elemento
           player.playSample(music.getFilename());//Reproduce el elemento guardado
           music.plusPlay();;//Aumenta en uno el plusplay 
           index++;//Se aumenta el indice
      }
       
       
    }
    
    /**
     * Add a track file to the collection.
     * @param filename The file name of the track to be added.
     */
    public void addFile(String filename)
    {
        tracks.add(new Track(filename));
    }
    
    /**
     * Add a track to the collection.
     * @param track The track to be added.
     */
    public void addTrack(Track track)
    {
        tracks.add(track);
    }
    
    /**
     * Play a track in the collection.
     * @param index The index of the track to be played.
     */
    public void playTrack(int index)
    {
        if(playMusic){
            System.out.println("se esta reproduciendo una cancion,pare y reinicie");
        }
        else{
                if(indexValid(index)) {
                    Track track = tracks.get(index);
                    player.startPlaying(track.getFilename());
                    playMusic = true;
                    System.out.println("Now playing: " + track.getArtist() + " - " + track.getTitle());
                    track.plusPlay();
                 }
         }
    }
    
    /**
     * Return the number of tracks in the collection.
     * @return The number of tracks in the collection.
     */
    public int getNumberOfTracks()
    {
        return tracks.size();
    }
    
    /**
     * List a track from the collection.
     * @param index The index of the track to be listed.
     */
    public void listTrack(int index)
    {
        System.out.print("Track " + index + ": ");
        Track track = tracks.get(index);
        System.out.println(track.getDetails());
    }
    
    /**
     * Show a list of all the tracks in the collection.
     */
    public void listAllTracks()
    {
        System.out.println("Track listing: ");

        for(Track track : tracks) {
            System.out.println(track.getDetails());
        }
        System.out.println();
    }
    
    /**
     * List all tracks by the given artist.
     * @param artist The artist's name.
     */
    public void listByArtist(String artist)
    {
        for(Track track : tracks) {
            if(track.getArtist().contains(artist)) {
                System.out.println(track.getDetails());
            }
        }
    }
    
    /**
     * Remove a track from the collection.
     * @param index The index of the track to be removed.
     */
    public void removeTrack(int index)
    {
        if(indexValid(index)) {
            tracks.remove(index);
        }
    }
    
    /**
     * Play the first track in the collection, if there is one.
     */
    public void playFirst()
    {
        if(playMusic){
            System.out.println("se esta reproduciendo una cancion, pare y reinicie");
        }
            else{
                if(tracks.size() > 0) {
                player.startPlaying(tracks.get(0).getFilename());
                playMusic = true;
                Track track = tracks.get(0);            
                track.plusPlay();
            }
        
       }
    }
    
    /**
     * Stop the player.
     */
    public void stopPlaying()
    {
        player.stop();
        playMusic = false;
    }

    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean indexValid(int index)
    {
        // The return value.
        // Set according to whether the index is valid or not.
        boolean valid;
        
        if(index < 0) {
            System.out.println("Index cannot be negative: " + index);
            valid = false;
        }
        else if(index >= tracks.size()) {
            System.out.println("Index is too large: " + index);
            valid = false;
        }
        else {
            valid = true;
        }
        return valid;
    }
    
    private void readLibrary(String folderName)
    {
        ArrayList<Track> tempTracks = reader.readTracks(folderName, ".mp3");

        // Put all thetracks into the organizer.
        for(Track track : tempTracks) {
            addTrack(track);
        }
    }
    
    /**
     * método a la clase MusicOrganizer llamado findInTitle que tome un único parámetro 
     * de tipo String y muestre por pantalla la información de los tracks que contienen 
     * dicha cadena en el título de la canción.
     */
    public void findInTitle(String trackName)
    {
        
        for(Track track : tracks) {
            if(track.getTitle().contains(trackName)) {
                System.out.println(track.getTitle());
            }
        }
       
    }
    
    /**
     * metodo para fijar productora en cada track
     */
    public void setProductora(int index, String productora)
    {
        tracks.get(index).setProductora(productora);
    }
    
    public void isPlaying()
    {
        if(playMusic) {
            System.out.println("se esta reproduciendo una cancion");
        }
        else{
             System.out.println(" no se esta reproduciendo una cancion");
        }
    }
}
