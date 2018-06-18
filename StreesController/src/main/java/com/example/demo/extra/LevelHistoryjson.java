package com.example.demo.extra;

import java.util.List;

import com.example.demo.Entity.StressLevelHistory;

import lombok.Data;

@Data
public class LevelHistoryjson {
	private String res_status;
	private List<StressLevelHistory> HistoryList;
}
