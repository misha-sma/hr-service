package candidate.data.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "event")
@Getter
@Setter
@AllArgsConstructor
public class Event {

	@Id
	@Column(name = "event_id")
	private UUID eventId;

	@Column(name = "candidate_id")
	private Integer candidateId;

	@Column(name = "message")
	private String message;

	@Column(name = "is_sent")
	private boolean isSent;

	@Column(name = "create_date")
	private LocalDateTime createDate;
}
