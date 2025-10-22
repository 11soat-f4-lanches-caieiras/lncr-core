package br.com.tp.lncr.core.applications.notification;

import br.com.tp.lncr.core.domain.notification.Notification;
import br.com.tp.lncr.core.dtos.notification.NotificationDTO;
import br.com.tp.lncr.core.interfaces.notification.NotificationGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CreateNotificationUseCaseTest {
    private NotificationGateway notificationGateway;
    private CreateNotificationUseCase useCase;

    @BeforeEach
    void setUp() {
        notificationGateway = mock(NotificationGateway.class);
        useCase = new CreateNotificationUseCase(notificationGateway);
    }

    @Test
    void deveCriarNotificacaoComSucesso() {
        NotificationDTO dto = mock(NotificationDTO.class);
        when(dto.getMessage()).thenReturn("Mensagem de teste");

        useCase.execute(dto);

        verify(notificationGateway).saveNotification(any(Notification.class));
    }
}
