import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;

public class ParIsbnId implements RegistroHashExtensivel<ParIsbnId> {

	private int id;
	private String ISBN;
	final private int TAMANHO = 12;

	public ParIsbnId(int i, int id2) {
		this(-1, "");
	}

	public ParIsbnId(int i, String e) {
		this.id = i;
		this.ISBN = e;
	}

	public int getId() {
		return id;
	}

	public String getISBN() {
		return ISBN;
	}

	public int hashCode() {
		return this.id;
	}

	public short size() {
		return TAMANHO;
	}

	public static Constructor<ParIsbnId> getConstructor() throws NoSuchMethodException, SecurityException {
		return ParIsbnId.class.getConstructor();
	}

	public byte[] toByteArray() throws IOException {
		ByteArrayOutputStream ba_out = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(ba_out);
		dos.writeInt(this.id);
        dos.writeUTF(this.ISBN);
		return ba_out.toByteArray();
	}

	public void fromByteArray(byte[] ba) throws IOException {
		ByteArrayInputStream ba_in = new ByteArrayInputStream(ba);
		DataInputStream dis = new DataInputStream(ba_in);
		this.id = dis.readInt();
        this.ISBN = dis.readUTF();
	}
}