                                                                                    import java.io.File;
import de.tudarmstadt.ukp.jwktl.JWKTL;
import de.tudarmstadt.ukp.jwktl.api.IQuotation;
import de.tudarmstadt.ukp.jwktl.api.IWikiString;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEdition;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEntry;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryExample;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryPage;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryRelation;
import de.tudarmstadt.ukp.jwktl.api.IWiktionarySense;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryTranslation;
import de.tudarmstadt.ukp.jwktl.api.PartOfSpeech;
import de.tudarmstadt.ukp.jwktl.api.RelationType;
import de.tudarmstadt.ukp.jwktl.api.filter.WiktionaryEntryFilter;
import de.tudarmstadt.ukp.jwktl.api.util.Language;

public class Dicionay  implements Runnable{

	String string, result;

	String output = "DicionaryDatabase";
	File wiktionaryDirectory = new File(output);
	IWiktionaryEdition wkt = JWKTL.openEdition(wiktionaryDirectory);

	public String searchWord(String word) {
		String wordReturn = word;
		System.out.println("palavraantestraducao"+word);
		IWiktionaryPage page = wkt.getPageForWord(word);
		if(page != null) {
			for (IWiktionaryEntry entry : page.getEntries())
				if (entry.getPartOfSpeech() == PartOfSpeech.NOUN)
					for (IWiktionaryTranslation translation : entry.getTranslations(Language.GERMAN))
						wordReturn += translation.getTranslation() +"\n";
		}else {
			System.out.println("\nnão achou palavra\n");
		}

		return wordReturn;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		setResult(searchWord(getString()));
	}
	

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
