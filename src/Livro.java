import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Livro implements Registro{
    private int id;
    private String titulo;
    private int ISBN;
    private int idCategoria;

    public Livro() {
        this(0, "", 0, 0);
    }

    public Livro(int id, String titulo, int iSBN, int idCategoria) {
        this.id = id;
        this.titulo = titulo;
        this.ISBN = iSBN;
        this.idCategoria = idCategoria;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public int getISBN() {
        return ISBN;
    }
    public void setISBN(int iSBN) {
        ISBN = iSBN;
    }
    public int getIdCategoria() {
        return idCategoria;
    }
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    
    @Override
    public byte[] toByteArray() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(id);
        dos.writeUTF(titulo);
        dos.writeInt(ISBN);
        dos.writeInt(idCategoria);
        return baos.toByteArray();
    }

    @Override
    public void fromByteArray(byte[] ba) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        setId(dis.readInt());
        setTitulo(dis.readUTF());
        setISBN(dis.readInt());
        setIdCategoria(dis.readInt());
    }

    @Override
    public String toString() {
        return "Livro [ISBN=" + ISBN + ", id=" + id + ", titulo=" + titulo + ", idCategoria=" + idCategoria + "]";
    }
}
