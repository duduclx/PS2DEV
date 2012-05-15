#include <stdio.h>

#include <SDL/SDL.h>
#include <SDL/SDL_endian.h>
#include <SDL_mixer.h>

static volatile int playing = 0;

int __errno()
{
	extern int errno;
	return errno;
}


static void music_finished()
{
	playing = 0;
}

static void print_format()
{
	int audio_channels;
	int audio_rate;
	Uint16 audio_format;

	Mix_QuerySpec(&audio_rate, &audio_format, &audio_channels);
	printf("Opened audio at %d Hz %d bit %s\n", audio_rate,
		(audio_format & 0xFF),
		(audio_channels > 2) ? "surround" : (audio_channels > 1) ? "stereo" : "mono");
}

int main(int argc, char *argv[])
{
	int error;
	char *sample;
	int sample_size;
	Mix_Music *music;
	SDL_RWops *rw;
	FILE *fp;

	if (argc != 2)
	{
		printf("usage: %s modfile\n", argv[0]);
		return 1;
	}

	/* initialize SDL */
	error = SDL_Init(SDL_INIT_AUDIO | SDL_INIT_TIMER);
	if (error != 0)
	{
		printf("SDL_GetError returned: %s\n", SDL_GetError());
		return 0;
	}

	atexit(SDL_Quit);

	/* initialize SDL_mixer */
	if (Mix_OpenAudio(44100, AUDIO_S16, 2, 512) < 0) 
	{
		printf("Couldn't open audio: %s\n", SDL_GetError());
		return 0;
	}

	print_format();

	/* load entire module to memory
	 *
	 * this is recommended since MikMod makes many many calls
 	 * to fread(), and it is quite slow with ps2client over IP
	 */
	printf("loading module '%s'..\n", argv[1]);
	fp = fopen(argv[1], "rb");
	if (fp == NULL)
	{
		printf("failed to open module %s\n", argv[1]);
		return 0;
	}

	fseek(fp, 0, SEEK_END);
	sample_size = ftell(fp);
	fseek(fp, 0, SEEK_SET);
	sample = (char *)malloc(sample_size);
	sample_size = fread(sample, 1, sample_size, fp);
	fclose(fp);

	printf("File loaded into memory\n");

	rw = SDL_RWFromMem(sample, sample_size);

	/* load and play music */
	music = Mix_LoadMUS_RW(rw);
	if (music == NULL)
	{
		printf("LoadMUS failed\n");
		return 0;
	}

	/* set a callback when module completes */
	playing = 1;
	Mix_HookMusicFinished(music_finished);

	/* play song once */
	Mix_PlayMusic(music, 0);

	printf("playing for 10 seconds\n");
	SDL_Delay(10*1000);

	printf("\n");
	printf("module ended\n");

        Mix_FreeMusic(music);
	return 0;
}
