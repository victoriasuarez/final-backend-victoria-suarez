package com.finalback.victoriasuarez.movies.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MOVIE")
public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "MOVIE_ID", unique = true, nullable = false)
	private Long movieId;

	@Column(name = "MOVIE_NAME", nullable = false, length = 50)
	private String name;

	@Basic (fetch = FetchType.LAZY)
	@Column(name = "MOVIE_GENRE", nullable = false, length = 50)
	private String genre;

	@Basic (fetch = FetchType.LAZY)
	@Column(name = "MOVIE_URL", nullable = false, length = 50)
	private String urlStream;

}
