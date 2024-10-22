import java.lang.reflect.Constructor;

public class ArquivoLivro extends Arquivo<Livro> {
    private HashExtensivel<ParIsbnId> indiceIndiretoIsbn;
    ArvoreBMais<Categoria> indiceDiretoId;

    public ArquivoLivro(String fn, Constructor<Livro> constructor) throws Exception {
        super(fn, constructor);
        indiceIndiretoIsbn = new HashExtensivel<>(ParIsbnId.class.getConstructor(), 4, "indiceIndiretoIsbn.hash_d.db", "indiceIndiretoIsbn.hash_c.db");
        indiceDiretoId = new ArvoreBMais<>(Categoria.class.getConstructor(), 4, "indiceDiretoId.db");
    }
    

    @Override
    public int create(Livro livro) throws Exception {
        int id = super.create(livro);
        livro.setId(id);
        indiceIndiretoIsbn.create(new ParIsbnId(livro.getISBN(), id));
        indiceDiretoId.create(new Categoria(livro.getIdCategoria(), ""));
        return id;
    }

    @Override
    public Livro read(int id) throws Exception {
        return super.read(id);
    }

    @Override
    public boolean update(Livro livro) throws Exception {
        Livro livroAntigo = read(livro.getId());
        if (super.update(livro)) {
            indiceIndiretoIsbn.delete(new ParIsbnId(livroAntigo.getISBN(), livro.getId()).getId());
            indiceIndiretoIsbn.create(new ParIsbnId(livro.getISBN(), livro.getId()));
            indiceDiretoId.delete(new Categoria(livroAntigo.getIdCategoria(), ""));
            indiceDiretoId.create(new Categoria(livro.getIdCategoria(), ""));
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) throws Exception {
        Livro livro = read(id);
        if (super.delete(id)) {
            indiceIndiretoIsbn.delete(new ParIsbnId(livro.getISBN(), id).getId());
            indiceDiretoId.delete(new Categoria(livro.getIdCategoria(), ""));
            return true;
        }
        return false;
    }
    
}
