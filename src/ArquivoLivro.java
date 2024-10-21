import java.lang.reflect.Constructor;

public class ArquivoLivro extends Arquivo<Livro> {
    private HashExtensivel<ParIsbnId> indiceIndiretoIsbn;

    public ArquivoLivro(String fn, Constructor<Livro> constructor) throws Exception {
        super(fn, constructor);
    }
    

    @Override
    public int create(Livro livro) throws Exception {
        int id = super.create(livro);
        livro.setId(id);
        indiceIndiretoIsbn.create(new ParIsbnId(livro.getISBN(), id));
        return id;
    }

    @Override
    public Livro read(int id) throws Exception {
        Livro livro = super.read(id);
        ParIsbnId par = indiceIndiretoIsbn.read(livro.getISBN());
        if (par != null && par.getId() == id) {
            return livro;
        }
        return null;
    }

    @Override
    public boolean update(Livro livro) throws Exception {
        if (super.update(livro)) {
            indiceIndiretoIsbn.update(new ParIsbnId(livro.getISBN(), livro.getId()));
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) throws Exception {
        Livro livro = read(id);
        if (super.delete(id)) {
            indiceIndiretoIsbn.delete(new ParIsbnId(livro.getISBN(), id).getId());
            return true;
        }
        return false;
    }

    
}
