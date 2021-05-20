package br.ufg.inf.fullstack.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufg.inf.fullstack.ctrl.business.OfertaBusiness;
import br.ufg.inf.fullstack.ctrl.exception.OfertaException;
import br.ufg.inf.fullstack.model.entities.Oferta;
import br.ufg.inf.fullstack.util.Message;

@CrossOrigin
@RestController
@RequestMapping(value="/ofertas")
public class OfertaCtrl {
	
	@Autowired
	private OfertaBusiness business;
	
	@GetMapping
	public ResponseEntity<List<Oferta>> findAll() {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		
		List<Oferta> list = business.findAll();
		if(list.size() == 0) {
			status = HttpStatus.NO_CONTENT;
			headers.add("message", Message.get("0211"));
		}
		return new ResponseEntity<List<Oferta>>(list, headers, status);
	}
	
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Oferta> findById(@PathVariable Integer id){
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		
		Oferta retorno = new Oferta();
		try {
			retorno = business.findById(id);
		} catch (OfertaException e) {
			headers.add("message", Message.get(e.getMessage()));
			status = HttpStatus.NO_CONTENT;
		} catch (Exception e) {
			headers.add("message", Message.get("0002"));
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Oferta>(retorno, headers, status);
	}
		
	@PostMapping
	public ResponseEntity<Oferta> insert(@RequestBody Oferta oferta){
		HttpStatus status = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();
	 
		try {
			oferta = business.insert(oferta);
			headers.add("message", Message.get("0301"));
		} catch (OfertaException e) {
			status = HttpStatus.BAD_REQUEST;
			headers.add("message", Message.get(e.getMessage()));
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			headers.add("message", Message.get("0302"));
		}
	
		return new ResponseEntity<Oferta>(oferta, headers, status);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete (@PathVariable Integer id){
		HttpStatus status = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();
		try {
			business.delete(id);
			headers.add("message", Message.get("0307"));
		}catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			headers.add("message", Message.get("0308"));
		}
		return new ResponseEntity<Void>(null, headers, status);
	}
	
	@PutMapping
	public ResponseEntity<Oferta> update(@RequestBody Oferta oferta){
		HttpStatus status = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();
		
		try {
			oferta = business.insert(oferta);
			headers.add("message", Message.get("0305"));
		} catch (OfertaException e) {
			status = HttpStatus.BAD_REQUEST;
			headers.add("message", Message.get(e.getMessage()));			
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			headers.add("message", Message.get("0306"));	
		}
		return new ResponseEntity<Oferta>(oferta, headers, status);
	}
	
}
