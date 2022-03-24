import com.opencsv.CSVWriter
import com.opencsv.bean.StatefulBeanToCsv
import com.opencsv.bean.StatefulBeanToCsvBuilder
import org.jsoup.Connection.Method
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

import java.nio.file.Files
import java.nio.file.Paths

class WebCrawler {
    static void main(String[] args) {
        List<String> urls = [
                'https://go.olx.com.br/grande-goiania-e-anapolis?q=iPhone%2011',
                'https://go.olx.com.br/grande-goiania-e-anapolis?o=2&q=iPhone%2011',
                'https://go.olx.com.br/grande-goiania-e-anapolis?o=3&q=iPhone%2011'
        ]
        List<Anuncio> anuncios = []
        for (String url in urls) {
            anuncios.addAll(obtemAnuncios(url))
        }
        anuncios = processaAnuncios(anuncios)
        geraArquivo(anuncios)
    }

    private static List<Anuncio> obtemAnuncios(String url) {
        Element anunciosPagina = Jsoup.connect(url).method(Method.GET).execute().parse().getElementById('ad-list')
        List<Anuncio> anuncios = []
        for (Element anuncio in anunciosPagina.childNodes() as List<Element>) {
            if (anuncio.childNodes().size() > 0) {
                Boolean temValor = anuncio.childNode(0).childNode(0).childNode(1).childNode(0).childNode(1).childNode(1).childNode(0).childNode(0).childNode(0).childNodes().size() != 0
                if(temValor) {
                    String valor = anuncio.childNode(0).childNode(0).childNode(1).childNode(0).childNode(1).childNode(1).childNode(0).childNode(0).childNode(0).childNode(0).toString()
                    String nome = anuncio.childNode(0).childNode(0).childNode(1).childNode(0).childNode(0).childNode(0).childNode(0).childNode(0).toString()
                    String urlAnuncio = anuncio.childNode(0).attributes().get('href')
                    String endereco = anuncio.childNode(0).childNode(0).childNode(1).childNode(1).childNode(0).childNode(0).childNode(0).childNode(0).toString()
                    valor = valor.replaceAll('(R\\$ )|\\.', '')
                    anuncios.add(new Anuncio(urlAnuncio, nome, endereco, valor))
                }
            }
        }
        return anuncios
    }

    private static List<Anuncio> processaAnuncios(List<Anuncio> anuncios) {
        BigDecimal valorTotal = BigDecimal.ZERO
        for (Anuncio anuncio in anuncios) {
            valorTotal = valorTotal + (new BigDecimal(anuncio.valor))
        }
        BigDecimal media = valorTotal / (new BigDecimal(anuncios.size()))
        List<Anuncio> anunciosAbaixoMedia = anuncios.findAll { Anuncio anuncio ->
            (new BigDecimal(anuncio.valor)) > media
        }
        anunciosAbaixoMedia.sort { Anuncio anuncio ->
            (new BigDecimal(anuncio.valor))
        }
        return anunciosAbaixoMedia
    }

    private static void geraArquivo(List<Anuncio> anuncios) {
        File file = new File('./anuncios.csv')
        FileWriter outputfile = new FileWriter(file)
        CSVWriter writer = new CSVWriter(outputfile)
        String[] header = ["Nome", "Valor", "Endereco", "Url"]
        writer.writeNext(header)
        for(Anuncio anuncio in anuncios) {
            String[] data = [anuncio.nome, anuncio.valor, anuncio.endereco, anuncio.url]
            writer.writeNext(data)
        }
        writer.close()
    }


}
