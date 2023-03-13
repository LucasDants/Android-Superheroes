package com.example.superheroes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.Hero
import com.example.superheroes.model.heroes
import com.example.superheroes.ui.theme.Shapes
import com.example.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                // A surface container using the 'background' color from the theme
                SuperHeroesApp()
            }
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SuperHeroesApp() {
    Scaffold(topBar = {
        SuperHeroesTopAppBar()
    }) {
        LazyColumn(modifier = Modifier.background(MaterialTheme.colors.background), contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
            items(heroes) {
                SuperHeroItem(hero = it)
            }
        }
    }
}

@Composable
fun SuperHeroesTopAppBar(modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .height(56.dp)
        .fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.h1)
    }

}


@Composable
fun SuperHeroItem(hero: Hero, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth().padding(bottom = 8.dp), elevation = 2.dp) {
        Row(modifier = Modifier.padding(16.dp).height(72.dp)) {
            SuperHeroInformation(hero.nameRes, hero.descriptionRes, modifier = Modifier.weight(1f))
            Image(painter = painterResource(id = hero.imageRes), contentDescription = null, modifier = Modifier.clip(
                Shapes.small
            ))
        }
    }
}

@Composable
fun SuperHeroInformation(@StringRes nameRes: Int, @StringRes descriptionRes: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = stringResource(id = nameRes), style = MaterialTheme.typography.h3)
        Text(text = stringResource(id = descriptionRes), style = MaterialTheme.typography.body1)
    }
}


@Preview
@Composable
fun SuperHeroesPreview() {
    SuperheroesTheme(darkTheme = false) {
        SuperHeroesApp()
    }
}

@Preview
@Composable
fun DarkThemePreview() {
    SuperheroesTheme(darkTheme = true) {
        SuperHeroesApp()
    }
}
