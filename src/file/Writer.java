package file;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.util.Vector;
import javax.management.relation.Relation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
public class Writer {
      public void writeObject(String title, Object o) throws Exception {
            File file = new File(title + ".dat");
            Vector v = new Vector();
            if ( file.length() == 0 || !file.exists()  ) {
                  FileOutputStream output = new FileOutputStream(file);
                  ObjectOutputStream writer = new ObjectOutputStream(output);
                  writer.writeObject(v);
                  output.close();
                  writer.close();
            }
            FileInputStream input = new FileInputStream(file);
            ObjectInputStream reader = new ObjectInputStream(input);
            v = (Vector) reader.readObject();
            v.add(o);
            input.close();
            reader.close();
            FileOutputStream output = new FileOutputStream(file);
            ObjectOutputStream writer = new ObjectOutputStream(output);
            writer.writeObject(v);
            output.close();
            writer.close();
            file.setExecutable(true,false);
            file.setReadable(true,false);
            file.setWritable(true,false);
      }
      public  Vector readObject(String title) throws Exception{
            title = title.trim();
            File file = new File(title + ".dat");
            if (!file.exists()) {
                  throw new Exception("LE FICHIER "+title+" N' EXISTE PAS");
            }
            FileInputStream input = new FileInputStream(file);
            ObjectInputStream reader = new ObjectInputStream(input);
            Vector v = (Vector) reader.readObject();
            input.close();
            reader.close();
            return v;
      }    
      public void writeJson(String title, Object o) throws Exception {
            File file = new File(title + ".json");
            Vector<Object> vector = new Vector<>();
            if (file.exists()) {
                  // Charger le vecteur existant depuis le fichier JSON
                  try (Reader reader = new FileReader(file)) {
                  Gson gson = new Gson();
                  vector = gson.fromJson(reader, Vector.class);
                  } catch (IOException e) {
                  e.printStackTrace();
                  throw new Exception("Erreur lors de la lecture du fichier JSON.");
                  }
            }
            // Ajouter l'objet au vecteur
            vector.add(o);
            // Enregistrer le vecteur au format JSON dans le fichier
            try (FileWriter writer = new FileWriter(file)) {
                  Gson gson = new Gson();
                  gson.toJson(vector, writer);
            } catch (IOException e) {
                  e.printStackTrace();
                  throw new Exception("Erreur lors de l'écriture du fichier JSON.");
            }
            // Rendre le fichier exécutable, lisible et inscriptible (peut être ajusté selon vos besoins)
            file.setExecutable(true, false);
            file.setReadable(true, false);
            file.setWritable(true, false);
      }
      
      public Vector<Object> readJson(String title) throws Exception {
            File file = new File(title + ".json");
            if (!file.exists()) {
                  return new Vector<>();
            }
            Vector<Object> vector;
            try (Reader reader = new FileReader(file)) {
                  Gson gson = new Gson();
                  Type type = new TypeToken<Vector<Point>>(){}.getType();
                  vector = gson.fromJson(reader, type);
            } catch (IOException e) {
                  e.printStackTrace();
                  throw new Exception("Erreur lors de la lecture du fichier JSON.");
            }
            return vector;
      }
    
}