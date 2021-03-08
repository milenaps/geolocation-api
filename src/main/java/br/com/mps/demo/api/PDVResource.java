package br.com.mps.demo.api;

import java.util.Optional;

import br.com.mps.demo.model.PDV;
import br.com.mps.demo.service.PDVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/pdvs")
public class PDVResource {

	@Autowired
    private PDVService service;

    @GetMapping
//	public ResponseEntity<Iterable<PDV>> list(@QuerydslPredicate(root = PDV.class) Predicate predicate) {
    public ResponseEntity<Iterable<PDV>> list(@RequestParam(name = "lat", required = false) Double lat,
                                              @RequestParam(name = "lng", required = false) Double lng) {
    	return ResponseEntity.ok(this.service.findAll(lat, lng));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PDV> read(@PathVariable("id") Long id) {
        Optional<PDV> pdv = this.service.find(id);
        if (!pdv.isPresent())
        	return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pdv.get());
    }

    @PostMapping
    public ResponseEntity<PDV> create(@Validated @RequestBody PDV pdv) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createOrUpdate(pdv));
    }

    @PutMapping
    public ResponseEntity<PDV> update(@Validated @RequestBody PDV pdv) {
        return ResponseEntity.ok(this.service.createOrUpdate(pdv));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.service.delete(id);
    }
}