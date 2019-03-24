package w1n.backend.springbackend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import w1n.backend.springbackend.model.Annuncio;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class AnnuncioService {

    final private List<Annuncio> books = new LinkedList<>();

    public Page<Annuncio> findPaginated(Pageable pageable) {
        Annuncio ann = new Annuncio();
        ann.setId("ID1");
        ann.setName("ANNUNCIO 1");

        books.add(ann);

        Annuncio ann2 = new Annuncio();
        ann2.setId("ID2");
        ann2.setName("ANNUNCIO 2");

        books.add(ann2);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Annuncio> list;

        if (books.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, books.size());
            list = books.subList(startItem, toIndex);
        }

        Page<Annuncio> bookPage
                = new PageImpl<Annuncio>(list, PageRequest.of(currentPage, pageSize), books.size());

        return bookPage;
    }










    public List<Annuncio> listaannunci = new LinkedList<>();



    public List<Annuncio> getAnnunciCorrente(){

       return this.listaannunci;

    }




    public List<Annuncio> annunciIniziali(){
        List<Annuncio> listaannunci = new LinkedList<>();

        for(int i = 0 ; i< 10 ; i++){
            Annuncio annuncio = new Annuncio();
            annuncio.setName("NOME ANNUNCIO " + i);
            annuncio.setId("ID ANNUNCIO " + i);
            listaannunci.add(annuncio);
        }
        this.listaannunci = listaannunci;
        return listaannunci;
    }






    public void annuncidopofiltro(int numeropaginacorrente){
        List<Annuncio> listaannunci = new LinkedList<>();


        for(int i = 10*numeropaginacorrente ; i< 10*numeropaginacorrente + 10 ; i++){
            Annuncio annuncio = new Annuncio();
            annuncio.setName("NOME ANNUNCIO " + i);
            annuncio.setId("ID ANNUNCIO " + i);
            listaannunci.add(annuncio);
        }
        this.listaannunci = listaannunci;
      //  return listaannunci;
    }





}
