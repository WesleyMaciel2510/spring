package com.example.spring;

import com.example.spring.builders.DocumentBuilder;
import com.example.spring.entity.Document;
import com.example.spring.entity.Usuario;
import com.example.spring.exception.BadRequestException;
import com.example.spring.exception.NotFoundException;
import com.example.spring.model.enums.DocumentTypeEnum;
import com.example.spring.model.enums.RoleEnum;
import com.example.spring.model.requests.IdRequest;
import com.example.spring.model.requests.document.DocumentCreateRequest;
import com.example.spring.model.requests.document.DocumentRequest;
import com.example.spring.model.requests.document.DocumentSaveListRequest;
import com.example.spring.model.requests.document.DocumentSearchRequest;
import com.example.spring.model.requests.document.DocumentUpdateRequest;
import com.example.spring.repository.DocumentRepository;
import com.example.spring.service.implementation.DocumentServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class DocumentServiceTest {

    @InjectMocks
    private DocumentServiceImpl service;

    @Mock
    private DocumentRepository repository;

    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    Document document = DocumentBuilder.document();

    @Test
    @DisplayName("Deve criar um Documento.")
    public void shouldCreateDocument() {
        String uuid = UUID.randomUUID().toString();

        Document document = Document.builder()
                .name("Documento Teste")
                .type(DocumentTypeEnum.LICENCIAMENTO)
                .active(true)
                .build();

        document.setId(uuid);

        when(repository.save(any(Document.class))).thenReturn(document);

        String idDocument = service.create(new DocumentCreateRequest(
                "Documento Teste",
                DocumentTypeEnum.LICENCIAMENTO,
                true)
        );

        assertNotNull(idDocument, "The returned ID should not be null.");
        assertEquals(uuid, idDocument, "The returned ID does not match the expected UUID.");
    }

    @Test
    @DisplayName("Deve atualizar um Documento.")
    public void shouldUpdateDocument() {
        String id = "cb40e93c-e773-4557-b07c-a5cd1b346208";

        DocumentUpdateRequest request = new DocumentUpdateRequest(
                id,
                "Documento Atualizado",
                DocumentTypeEnum.LICENCIAMENTO,
                false,
                true
        );

        Document existingDocument = Document.builder()
                .name("Documento Teste")
                .type(DocumentTypeEnum.CADASTRAL)
                .active(false)
                .build();
        existingDocument.setId(id);

        Document updatedDocument = Document.builder()
                .name("Documento Atualizado")
                .type(DocumentTypeEnum.LICENCIAMENTO)
                .active(true)
                .build();
        updatedDocument.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(existingDocument));
        when(repository.save(any(Document.class))).thenReturn(updatedDocument);

        String actualId = service.update(request);

        assertNotNull(actualId, "The returned ID should not be null.");
        assertEquals(id, actualId, "The returned ID does not match the expected ID.");

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(any(Document.class));
    }

    @Test
    @DisplayName("Deve marcar como excluído (e não deletar) um Documento.")
    public void shouldMarkAsDeletedDocument() {
        String id = "cb40e93c-e773-4557-b07c-a5cd1b346208";
        IdRequest request = new IdRequest(id);
        String expectedMessage = "Documento marcado como excluído com sucesso.";

        Document document = new Document();

        when(repository.findById(id)).thenReturn(Optional.of(document));
        when(repository.save(any(Document.class))).thenAnswer(invocation -> {
            Document savedDocument = invocation.getArgument(0);
            savedDocument.setDeleted(true);
            return savedDocument;
        });

        String actualMessage = service.markAsDeleted(request);

        assertNotNull(actualMessage, "The returned message should not be null.");
        assertEquals(expectedMessage, actualMessage, "The returned message does not match the expected message.");

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(any(Document.class));
    }

    @Test
    @DisplayName("Deve buscar o Documento pelo ID informado")
    public void shouldFindDocumentByID() {
        String uuid = UUID.randomUUID().toString();

        Document document = new Document();
        document.setId(uuid);
        document.setName("Documento Teste");

        IdRequest request = new IdRequest(uuid);

        when(repository.findById(uuid)).thenReturn(Optional.of(document));

        Document result = service.findById(request);

        assertNotNull(result, "The result should not be null.");
        assertEquals(uuid, result.getId(), "The ID of the result does not match the expected ID.");
        assertEquals("Documento Teste", result.getName(), "The name of the result does not match the expected name.");
    }

    @Test
    @DisplayName("Deve lançar BadRequestException quando o ID não for informado.")
    public void shouldThrowBadRequestExceptionWhenIdNotProvided() {
        IdRequest request = new IdRequest(null);

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            service.findById(request);
        });

        assertEquals("ID não informado.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar NotFoundException quando o documento não for encontrado.")
    public void shouldThrowNotFoundExceptionWhenDocumentNotFound() {
        String uuid = UUID.randomUUID().toString();
        IdRequest request = new IdRequest(uuid);

        when(repository.findById(uuid)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            service.findById(request);
        });

        assertEquals("Documento não encontrado.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve buscar o Documento Paginado")
    public void shouldSearchDocument() {
        String uuid = UUID.randomUUID().toString();

        document.setId(uuid);

        List<Document> documentList = new ArrayList<>();
        documentList.add(document);
        Page<Document> documentPage = new PageImpl<>(
                documentList, PageRequest.of(0, 10), 1
        );

        DocumentSearchRequest request = new DocumentSearchRequest(
                "Teste",
                DocumentTypeEnum.LICENCIAMENTO,
                true,
                0,
                10,
                null
        );

        when(repository.search(
                "Teste",
                DocumentTypeEnum.LICENCIAMENTO,
                true,
                PageRequest.of(0, 10)
        )).thenReturn(documentPage);

        Page<Document> currentPage = service.search(request);

        assertNotNull(currentPage);
        assertEquals(documentPage.getTotalElements(), currentPage.getTotalElements());
        assertEquals(documentPage.getContent().size(), currentPage.getContent().size());
        assertEquals(documentPage.getContent().get(0).getId(), currentPage.getContent().get(0).getId());

        verify(repository, times(1)).search(
                eq("Teste"),
                eq(DocumentTypeEnum.LICENCIAMENTO),
                eq(true),
                any(PageRequest.class)
        );
    }

    @Test
    @DisplayName("Deve salvar uma lista de Documentos.")
    public void shouldSaveListOfDocuments() {
        Document document = Document.builder()
                .name("Minuta de Prestação de Serviços")
                .type(DocumentTypeEnum.CONTRATO)
                .active(true)
                .build();

        DocumentRequest documentRequest = new DocumentRequest(
                null,
                "23/09/2024T12:00:00",
                "31/12/2024T23:59:59",
                document
        );

        Usuario usuario = Usuario.builder()
                .guid(UUID.randomUUID().toString())
                .username("wesley2510")
                .password("securePassword")
                .role(RoleEnum.ADMIN)
                .build();

        DocumentSaveListRequest request = new DocumentSaveListRequest(usuario, List.of(documentRequest));

        when(repository.save(any(Document.class))).thenAnswer(invocation -> invocation.getArgument(0));

        List<String> savedIds = service.saveList(request);

        verify(repository).save(any(Document.class));
    }

}