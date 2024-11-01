package com.example.spring.openApi;

import com.example.spring.entity.Document;
import com.example.spring.exceptionhandler.ErrorHandlerResponse;
import com.example.spring.model.requests.IdRequest;
import com.example.spring.model.requests.document.DocumentCreateRequest;
import com.example.spring.model.requests.document.DocumentSaveListRequest;
import com.example.spring.model.requests.document.DocumentUpdateRequest;
import com.example.spring.model.response.DefaultResponse;
import com.example.spring.model.response.IdResponse;
import com.example.spring.model.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Documento", description = "Serviços relacionados a entidade 'Documentos'")
public interface DocumentControllerOpenAPI {

    @Operation(summary = "Pesquisar Documentos", description = "Realiza a pesquisa de documentos com base nos filtros fornecidos. OBS.: Se o parâmetro `pageSize` for nulo, todos os dados serão retornados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode
                    = "200", description = "Pesquisa realizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.",
                    content = @Content(schema = @Schema(implementation = ErrorHandlerResponse.class)))
    })
    ResponseEntity<PageResponse<Document>> search(
            @Parameter(description = "Request com filtros de pesquisa de documentos")
            @RequestBody com.example.spring.model.requests.document.DocumentSearchRequest request);

    @Operation(summary = "Buscar Documento por ID", description = "Busca um documento pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Documento encontrado."),
            @ApiResponse(responseCode = "404", description = "Documento não encontrado.",
                    content = @Content(schema = @Schema(implementation = ErrorHandlerResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.",
                    content = @Content(schema = @Schema(implementation = ErrorHandlerResponse.class)))
    })
    ResponseEntity<Document> findById(
            @Parameter(description = "Request contendo o ID do documento", required = true)
            @RequestBody IdRequest request);

    @Operation(summary = "Criar Documento", description = "Cria um novo documento com os dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Documento criado com sucesso.",
                    content = @Content(schema = @Schema(implementation = ErrorHandlerResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.",
                    content = @Content(schema = @Schema(implementation = ErrorHandlerResponse.class)))
    })
    ResponseEntity<IdResponse> create(
            @Parameter(description = "Request contendo os dados para criar um novo documento", required = true)
            @RequestBody DocumentCreateRequest request);

    @Operation(summary = "Criar/Atualizar lista de Documentos", description = "Cria ou atualiza uma lista de documentos com base nos dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Documentos criados/atualizados com sucesso, retorna os IDs."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para criação/atualização.",
                    content = @Content(schema = @Schema(implementation = ErrorHandlerResponse.class)))
    })
    ResponseEntity<List<String>> saveList(
            @Parameter(description = "Caso coloque o ID na lista, atualizará o registro.", required = true)
            @RequestBody DocumentSaveListRequest request);

    @Operation(summary = "Atualizar Documento", description = "Atualiza as informações de um documento existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Documento atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Documento não encontrado.",
                    content = @Content(schema = @Schema(implementation = ErrorHandlerResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.",
                    content = @Content(schema = @Schema(implementation = ErrorHandlerResponse.class))),
    })
    ResponseEntity<IdResponse> update(
            @Parameter(description = "Request contendo os dados atualizados do documento", required = true)
            @RequestBody DocumentUpdateRequest request);

    @Operation(summary = "Excluir Documento", description = "Marca um documento como excluído.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Documento excluído com sucesso."),
            @ApiResponse(responseCode = "404", description = "Documento não encontrado.",
                    content = @Content(schema = @Schema(implementation = ErrorHandlerResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.",
                    content = @Content(schema = @Schema(implementation = ErrorHandlerResponse.class)))
    })
    ResponseEntity<DefaultResponse> markAsDeleted(
            @Parameter(description = "Request contendo o ID do documento a ser excluído", required = true)
            @RequestBody IdRequest request);
}
