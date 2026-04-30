package candidate.data.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "event")
@Getter
@Setter
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id")
	private Integer eventId;

	@Column(name = "candidate_id")
	private Integer candidateId;

	@Column(name = "message")
	private String message;

	@Column(name = "is_sent")
	private boolean isSent;

	@Column(name = "create_date")
	private LocalDateTime createDate;
}
