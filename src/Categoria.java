import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Categoria implements RegistroArvoreBMais<Categoria> {
    private int id;
    private String nome;

    public Categoria() {
        this(0, "");
    }

    public Categoria(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public short size() {
        return (short) (4 + nome.length() * 2);
    }

    @Override
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(id);
        dos.writeUTF(nome);
        return baos.toByteArray();
    }

    @Override
    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        try {
            id = dis.readInt();
            nome = dis.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(Categoria categoria) {
        return id - categoria.getId();
    }

    @Override
    public Categoria clone() {
        return new Categoria(id, nome);
    }
}
