
package UI;

import entity.Article;
import entity.Book;
import entity.FType;
import entity.Field;
import entity.Inproceedings;
import java.util.ArrayList;
import java.util.List;
import repositories.GenericRepository;

public class TestDataPopulator {

    public static void populateTestdata(GenericRepository repository) {
        for (int i = 0; i < 50; i++) {
            List<Field> inf = new ArrayList<Field>();
            inf.add(new Field(FType.referenceId, "referenceId" + i, true));
            inf.add(new Field(FType.author, "Author" + i, true));
            inf.add(new Field(FType.title, "Title" + i, true));
            inf.add(new Field(FType.booktitle, "Booktitle" + i, true));
            inf.add(new Field(FType.year, "1988" + i, true));
            inf.add(new Field(FType.editor, "Editor" + i, false));
            inf.add(new Field(FType.volume, "VolumeNumber" + i, false));
            inf.add(new Field(FType.series, "Series" + i, false));
            inf.add(new Field(FType.pages, "Pages" + i, false));
            inf.add(new Field(FType.address, "Address" + i, false));
            inf.add(new Field(FType.month, "Month" + i, false));
            inf.add(new Field(FType.organization, "Organization" + i, false));
            inf.add(new Field(FType.publisher, "Publisher" + i, false));
            inf.add(new Field(FType.note, "Note" + i, false));
            inf.add(new Field(FType.key, "Key" + i, false));
            Inproceedings in = new Inproceedings(inf);
            repository.create(in);

            List<Field> bf = new ArrayList<Field>();
            bf.add(new Field(FType.referenceId, "referenceId" + i, true));
            bf.add(new Field(FType.author, "author" + i, true));
            bf.add(new Field(FType.title, "title" + i, true));
            bf.add(new Field(FType.publisher, "publisher" + i, true));
            bf.add(new Field(FType.year, "year" + i, true));
            bf.add(new Field(FType.volume, "volume" + i, false));
            bf.add(new Field(FType.series, "series" + i, false));
            bf.add(new Field(FType.address, "address" + i, false));
            bf.add(new Field(FType.edition, "edition" + i, false));
            bf.add(new Field(FType.month, "month" + i, false));
            bf.add(new Field(FType.note, "note" + i, false));
            bf.add(new Field(FType.key, "key" + i, false));
            Book b = new Book(bf);
            repository.create(b);

            List<Field> af = new ArrayList<Field>();
            af.add(new Field(FType.referenceId, "referenceId" + i, true));
            af.add(new Field(FType.author, "author" + i, true));
            af.add(new Field(FType.title, "title" + i, true));
            af.add(new Field(FType.journal, "journal" + i, true));
            af.add(new Field(FType.year, "year" + i, true));

            af.add(new Field(FType.volume, "volume" + i, false));
            af.add(new Field(FType.number, "number" + i, false));
            af.add(new Field(FType.pages, "pages" + i, false));
            af.add(new Field(FType.month, "month" + i, false));
            af.add(new Field(FType.note, "note" + i, false));
            af.add(new Field(FType.key, "key" + i, false));
            Article a = new Article(af);
            repository.create(a);
        }
    }
}
