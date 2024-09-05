package com.example.spring.openApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Parameter;

@Tag(name = "Example", description = "Serviços de exemplo")
public interface ExampleControllerOpenApi {

    @Operation(summary = "Recebe uma mensagem de teste Hello World")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successo"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida."),
    })
    ResponseEntity<String> getHello(@Parameter(name = "value", required = true) String value);

}
