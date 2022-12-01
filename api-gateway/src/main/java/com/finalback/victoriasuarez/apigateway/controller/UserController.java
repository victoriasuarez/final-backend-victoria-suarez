package com.finalback.victoriasuarez.apigateway.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@RestController
public class UserController {
//
//    @Autowired
//    private RestTemplate restTemplate;

    //EJEMPLOO
//	@PostMapping("/{idOrigen}/transferir/{idDestino}")
//	public ResponseEntity<String> transferirMonto(@PathVariable("idOrigen") Long idCuentaOrigen
//			, @PathVariable("idDestino") Long idCuentaDestino, @RequestBody Double monto){
//
//		log.info("Transfiriendo : " + monto + ", cuenta origen : " + idCuentaOrigen
//				+ ", cuenta destino :" + idCuentaDestino);
//
//
//		restTemplate.exchange("http://localhost:9092/limite/validar/" + idCuentaOrigen , HttpMethod.POST,
//				new HttpEntity<Double>(monto) , String.class);
//
//		return ResponseEntity.ok("TransferenciaRealizada");
//	}

    @GetMapping("/user/login")
    public Map<String, Object> login(@RequestHeader HttpHeaders headers,@AuthenticationPrincipal OAuth2User principal) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("SESSION",headers.get(HttpHeaders.COOKIE) );
        result.put("USER",principal);
        return result;
    }


    @RequestMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }

}
