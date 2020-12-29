package com.example.canieat.model

import java.io.Serializable


data class SearchItem(
    val allergy: String,
    val barcode: String,
    val capacity: String,
    val imgurl1: String,
    val imgurl2: String,
    val manufacture: String,
    val nutrient: String,
    val prdkind: String,
    val prdkindstate: String,
    val prdlstNm: String,
    val prdlstReportNo: String,
    val productGb: String,
    val rawmtrl: String,
    val rnum: String,
    val seller: String
):Serializable