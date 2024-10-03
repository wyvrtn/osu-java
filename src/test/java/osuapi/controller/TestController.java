package osuapi.controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import osuapi.client.core.OsuApiClient;
import osuapi.models.beatmaps.Beatmap;

@RestController
@RequestMapping("/v2")
public class TestController {
	private static final Logger LOG = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private OsuApiClient client;
	
	@GetMapping("/beatmaps/{id}")
	public ResponseEntity<Beatmap> findBeatmapById(@PathVariable("id") int id) {
		LOG.debug("Find beatmap by id {}", id);
		Future<Beatmap> future = client.endpoints.lookupBeatmapId(Integer.toString(id));
		Beatmap out = new Beatmap();
		try {
			out = future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		    Thread.currentThread().interrupt();
		}
		return new ResponseEntity<>(out, HttpStatus.OK);
	}
}
