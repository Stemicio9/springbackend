package w1n.backend.springbackend.viecontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import w1n.backend.springbackend.model.Annuncio;
import w1n.backend.springbackend.services.AnnuncioService;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class AnnuncioController {

    @Autowired
    private AnnuncioService bookService;

    @RequestMapping(value = "/annunci", method = RequestMethod.GET)
    public ModelAndView listBooks(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        ModelAndView modelAndView = new ModelAndView();

        Page<Annuncio> bookPage = bookService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        modelAndView.addObject("bookPage", bookPage);

        int totalPages = bookPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }



        modelAndView.setViewName("annunci");
        return modelAndView;

    }

}
