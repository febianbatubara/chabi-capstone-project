package com.chabi.android.chabiapp.utils

import com.chabi.android.chabiapp.data.source.local.entity.OptionEntity
import com.chabi.android.chabiapp.data.source.local.entity.QuestionEntity

object QuestionDataFactory {

    fun getAgeQuestion(): QuestionEntity =
        QuestionEntity(
            100,
            "Pilih rentang usia anak",
            "Choose your age range",
            OptionEntity(
                "5 tahun ke bawah",
                "0"
            ),
            OptionEntity(
                "6 tahun k eatas",
                "1"
            ),
            1
        )

    fun generatePreschoolerQuestion(): List<QuestionEntity> {
        val questions = ArrayList<QuestionEntity>()
        questions.add(
            QuestionEntity(
                1,
                "Bagaimana biasanya anak anda merespons pengalaman baru?",
                "With a new experience, how does your kiddo typically respond? ",
                OptionEntity(
                    "Berdiam diri dan memperhatikan terlebih dahulu",
                    "Stand to the side and watch first. "
                ),
                OptionEntity(
                    "Bersemangat dan langsung bergabung",
                    "Gets excited and jumps right in. "
                ),
                1
            )
        )

        questions.add(
            QuestionEntity(
                2,
                "Apa yang biasanya paling disukai anak Anda?",
                "What does your kiddo usually enjoy most? ",
                OptionEntity(
                    "Bepergian dan melakukan eksplorasi",
                    "Going and doing. "
                ),
                OptionEntity(
                    "Tinggal di rumah dan bermain dengan tenang",
                    "Staying home and playing quietly. "
                ),
                2
            )
        )

        questions.add(
            QuestionEntity(
                3,
                "Apakah anak Anda ...",
                "Is your kiddo... ",
                OptionEntity(
                    "Tenang dan pendiam",
                    "Quiet and reserved? "
                ),
                OptionEntity(
                    "Ekspresif dan senang berbicara?",
                    "Expressive and talkative? "
                ),
                3
            )
        )

        questions.add(
            QuestionEntity(
                4,
                "Apakah anak Anda lebih suka...",
                "Does your kiddo prefer... ",
                OptionEntity(
                    "Beraktivitas dan ingin berpartisipasi dalam tindakan?",
                    "Activity and want to participate in the action? "
                ),
                OptionEntity(
                    "Kesendirian dan ketenangan; lebih suka bermain sendiri",
                    "Solitude and calm; prefers to play alone. "
                ),
                4
            )
        )

        questions.add(
            QuestionEntity(
                5,
                "Saat memproses suatu informasi, apakah anak anda",
                "When processing information, does your kiddo ",
                OptionEntity(
                    "Membicarakan semuanya",
                    "Talk things out. "
                ),
                OptionEntity(
                    "Memikirkan semuanya",
                    "Think things through. "
                ),
                5
            )
        )

        questions.add(
            QuestionEntity(
                6,
                "Apakah anak Anda lebih suka bermain dengan...",
                "Does your kiddo prefer to play with... ",
                OptionEntity(
                    "Beberapa teman dekat?",
                    "A few close friends? "
                ),
                OptionEntity(
                    "Banyak teman?",
                    "Lots of friends? "
                ),
                6
            )
        )

        questions.add(
            QuestionEntity(
                7,
                "Ketika ditanya, bagaimana biasanya anak Anda merespons?",
                "When asked a question, how does your kiddo typically respond? ",
                OptionEntity(
                    "Menjawab dengan cepat",
                    "Quickly. Doesn't skip a beat. "
                ),
                OptionEntity(
                    "Berhenti sejenak untuk berpikir",
                    "Pauses for a minute to think. "
                ),
                7
            )
        )

        questions.add(
            QuestionEntity(
                8,
                "Saat dihadapkan pada keputusan, mana yang paling cenderung dilakukan anak Anda?",
                "When faced with a decision, which does your kiddo tend to do most? ",
                OptionEntity(
                    "Berhenti sejenak dan berpikir",
                    "Stop and Think. "
                ),
                OptionEntity(
                    "Pergi dan lakukan",
                    "Go and Do. "
                ),
                8
            )
        )

        questions.add(
            QuestionEntity(
                9,
                "Mana yang paling menggambarkan anak Anda?",
                "Which one sums up your kiddo best? ",
                OptionEntity(
                    "Bersuara keras",
                    "Loud. "
                ),
                OptionEntity(
                    "Bersuara lembut",
                    "Soft-spoken. "
                ),
                9
            )
        )

        return questions
    }

    fun generateSchoolAgedQuestion(): List<QuestionEntity> {
        val questions = ArrayList<QuestionEntity>()
        questions.add(
            QuestionEntity(
                10,
                "Terhadapt pengalaman baru, apakah kamu awalnya...",
                "With a new experience, do you initially... ",
                OptionEntity(
                    "Ingin mengamati terlebih dahulu?",
                    "want to observe first? "
                ),
                OptionEntity(
                    "Langsung mencoba?",
                    "jump right in? "
                ),
                1
            )
        )

        questions.add(
            QuestionEntity(
                11,
                "Saat kamu membaca sebuah cerita, apakah kamu cenderung...",
                "When you read a story, are you more likely to... ",
                OptionEntity(
                    "Memperhatikan dan mengingat detail spesifiknya?",
                    "notice and remember the specific details? "
                ),
                OptionEntity(
                    "Memperhatikan pola dan temanya?",
                    "notice the patterns and themes? "
                ),
                2
            )
        )

        questions.add(
            QuestionEntity(
                12,
                "Mana yang cenderung lebih kamu khawatirkan?",
                "Which do you tend to be more concerned with? ",
                OptionEntity(
                    "Kejujuran",
                    "Honesty. "
                ),
                OptionEntity(
                    "Harmoni",
                    "Harmony. "
                ),
                3
            )
        )

        questions.add(
            QuestionEntity(
                13,
                "Apakah kamu lebih suka...",
                "Would you rather... ",
                OptionEntity(
                    "Menjelajah dan mengikuti arus?",
                    "Explore and go with the flow? "
                ),
                OptionEntity(
                    "Punya rencana dan tahu apa yang diharapkan?",
                    "Have a plan and know what to expect? "
                ),
                4
            )
        )

        questions.add(
            QuestionEntity(
                14,
                "Mana yang lebih kamu sukai?",
                "Which would you rather engage in? ",
                OptionEntity(
                    "Aktivitas di sekitar kamu",
                    "Activities around you. "
                ),
                OptionEntity(
                    "Ide kreatif yang ada di pikiran kamu",
                    "The creative ideas that are in your head. "
                ),
                5
            )
        )

        questions.add(
            QuestionEntity(
                15,
                "Jika orang tuamu memberimu daftar tugas, apakah kamu...",
                "If your parent gave you a to-do list, would you ",
                OptionEntity(
                    "Mengerjakan masing-masing secara berurutan? contoh: pertama, kedua, ketiga...",
                    "Go through each one sequentially? ex: first, second, third "
                ),
                OptionEntity(
                    "Menyelesaikan yang ingin kamu lakukan terlebih dahulu?",
                    "Bounce around the list - completing the ones you want to do first? "
                ),
                6
            )
        )

        questions.add(
            QuestionEntity(
                16,
                "Guru seperti apa yang/cenderung kamu sukai?",
                "What kinds of teachers did/do you tend to like? ",
                OptionEntity(
                    "Adil dan konsisten",
                    "Fair and consistent. "
                ),
                OptionEntity(
                    "Penyayang dan baik hati",
                    "Loving and kind. "
                ),
                7
            )
        )

        questions.add(
            QuestionEntity(
                17,
                "Ketika dihadapkan dengan sebuah proyek atau pekerjaan rumah, apakah kamu menyelesaikannya ...",
                "When it comes to a project or homework, do you get it done... ",
                OptionEntity(
                    "pada menit-menit terakhir",
                    "at the last minute? "
                ),
                OptionEntity(
                    "segera?",
                    "right away? "
                ),
                8
            )
        )

        questions.add(
            QuestionEntity(
                18,
                "Apakah kamu lebih suka menghabiskan waktu bersama...",
                "Do you prefer to spend time with... ",
                OptionEntity(
                    "satu teman pada satu waktu?",
                    "one friend at a time? "
                ),
                OptionEntity(
                    "banyak teman sekaligus?",
                    "multiple friends at the same time? "
                ),
                9
            )
        )

        questions.add(
            QuestionEntity(
                21,
                "Apakah kamu cenderung ...",
                "Do you tend to... ",
                OptionEntity(
                    "bermimpi tentang apa yang dapat kamu lakukan suatu hari nanti?",
                    "dream about what you can do someday? "
                ),
                OptionEntity(
                    "bicara tentang apa yang dapat kamu lakukan sekarang?",
                    "talk about what you can do now? "
                ),
                10
            )
        )

        questions.add(
            QuestionEntity(
                22,
                "Jika teman sekelas kamu ketahuan menyontek saat ujian, bagaimana kamu menyarankan agar dia didisiplinkan? Haruskah dia diajarkan...",
                "If your classmate was caught cheating on a test, how would you suggest he be disciplined? Should he be taught... ",
                OptionEntity(
                    "dengan memberikan konsekuensi?",
                    "by given a consequence? "
                ),
                OptionEntity(
                    "dengan diajak bicara?",
                    "by being talked to? "
                ),
                11
            )
        )

        questions.add(
            QuestionEntity(
                23,
                "Apakah kamu cenderung...",
                "Do you tend to... ",
                OptionEntity(
                    "Memulai proyek baru sebelum menyelesaikan proyek kamu saat ini?",
                    "Start a new project before completing your current one? "
                ),
                OptionEntity(
                    "Menyelesaikan proyek sebelum memulai yang baru?",
                    "Finish a project before starting a new one? "
                ),
                12
            )
        )

        questions.add(
            QuestionEntity(
                24,
                "Apakah kamu lebih suka mengerjakan proyek...",
                "Do you prefer to work on projects... ",
                OptionEntity(
                    "dengan masukan dan bantuan orang lain?",
                    "with others input and help? "
                ),
                OptionEntity(
                    "sendiri?",
                    "by yourself? "
                ),
                13
            )
        )

        questions.add(
            QuestionEntity(
                25,
                "Apakah kamu...",
                "Do you... ",
                OptionEntity(
                    "cenderung memperhatikan detail di sekitar kamu?",
                    "tend to notice details around you? "
                ),
                OptionEntity(
                    "cenderung tidak aktif di duniamu sendiri?",
                    "tend to be off in your own world? "
                ),
                14
            )
        )

        questions.add(
            QuestionEntity(
                26,
                "Saat memproses informasi, apakah kamu cenderung...",
                "When processing information, do you tend to... ",
                OptionEntity(
                    "membicarakan hal-hal dengan orang lain?",
                    "talk things out with others? "
                ),
                OptionEntity(
                    "memikirkan semuanya? (atau bahkan mungkin menulis!)",
                    "think things through? (or maybe even write!) "
                ),
                15
            )
        )

        questions.add(
            QuestionEntity(
                27,
                "Apakah kamu cenderung berpikir...",
                "Do you tend to think... ",
                OptionEntity(
                    "secara harfiah?",
                    "literally? "
                ),
                OptionEntity(
                    "secara imajinatif?",
                    "imaginatively? "
                ),
                16
            )
        )

        questions.add(
            QuestionEntity(
                28,
                "Jika kamu menyaksikan anak lain di-bully, apa reaksi awal kamu?",
                "If you witnessed another child being bullied, what would your initial reaction be? ",
                OptionEntity(
                    "Bereaksi terhadap ketidakadilan dan menghadapi pengganggu.",
                    "React to the injustice and confront the bully. "
                ),
                OptionEntity(
                    "Bereaksi terhadap korban untuk melihat apakah mereka baik-baik saja.",
                    "React to the victim to see if they are okay. "
                ),
                17
            )
        )

        questions.add(
            QuestionEntity(
                29,
                "Dalam hal pengorganisasian, apakah kamu...",
                "When it comes to organizing, do you... ",
                OptionEntity(
                    "meletakkan barang-barang di mana kamu dapat menemukannya?",
                    "put things away where you can find them?. "
                ),
                OptionEntity(
                    "meninggalkan hal-hal di mana kamu dapat menemukannya?",
                    "leave things out where you can find them?. "
                ),
                18
            )
        )

        questions.add(
            QuestionEntity(
                30,
                "Apakah kamu mempunyai...",
                "Do you have... ",
                OptionEntity(
                    "beberapa teman dekat?",
                    "a few close friends?. "
                ),
                OptionEntity(
                    "banyak teman?",
                    "lots of friends?. "
                ),
                19
            )
        )

        questions.add(
            QuestionEntity(
                31,
                "Jika seseorang yang kamu sayangi sedih, apa yang akan kamu lakukan?",
                "If someone you care about is sad, what would you do? ",
                OptionEntity(
                    "Mencoba membuat mereka tersenyum dan merasa bahagia.",
                    "try to make them smile and feel happy. "
                ),
                OptionEntity(
                    "Mencoba untuk memperbaikinya dan memecahkan masalah mereka.",
                    "try to fix it and solve their problem. "
                ),
                20
            )
        )

        questions.add(
            QuestionEntity(
                32,
                "Apakah kamu...",
                "Do you... ",
                OptionEntity(
                    "senang mengetahui rencana?",
                    "enjoy knowing the plan?. "
                ),
                OptionEntity(
                    "menikmati spontanitas membuat rencana saat kamu pergi?",
                    "enjoy the spontaneity of making up the plan as you go?. "
                ),
                21
            )
        )

        questions.add(
            QuestionEntity(
                33,
                "Pilih opsi yang paling menggambarkan diri kamu:",
                "Choose the option that best describes you: ",
                OptionEntity(
                    "Senang berbicara",
                    "Talkative. "
                ),
                OptionEntity(
                    "Tenang",
                    "Quiet. "
                ),
                22
            )
        )

        questions.add(
            QuestionEntity(
                34,
                "Jika seseorang mengubah semua foto keluarga di ruang tamu kamu, apakah kamu akan langsung menyadarinya?",
                "If someone changed all the family photos in your living room, would you notice right away? ",
                OptionEntity(
                    "Ya",
                    "Yes. "
                ),
                OptionEntity(
                    "Tidak",
                    "No. "
                ),
                23
            )
        )

        questions.add(
            QuestionEntity(
                35,
                "Kamu lebih suka yang mana?",
                "Which do you prefer? ",
                OptionEntity(
                    "Sebuah buku non-fiksi (kisah kehidupan nyata.)",
                    "Non-fiction books (real life stories). "
                ),
                OptionEntity(
                    "Buku fiksi (cerita imajinasi atau fantasi).",
                    "Fiction books (imagination or fantasy stories). "
                ),
                24
            )
        )

        questions.add(
            QuestionEntity(
                36,
                "Apakah kamu cenderung membuat keputusan",
                "Do you tend to make decisions ",
                OptionEntity(
                    "dengan cepat?",
                    "quickly? "
                ),
                OptionEntity(
                    "pelan-pelan?",
                    "slowly? "
                ),
                25
            )
        )

        questions.add(
            QuestionEntity(
                37,
                "Pilih opsi yang paling menggambarkan dirimu.",
                "Choose the option that best describes you. ",
                OptionEntity(
                    "Berhenti dan berpikir",
                    "Stop and think. "
                ),
                OptionEntity(
                    "Pergi dan lakukan",
                    "Go and do. "
                ),
                26
            )
        )

        questions.add(
            QuestionEntity(
                38,
                "Apakah kamu cenderung ...",
                "Do you tend to... ",
                OptionEntity(
                    "melamun?",
                    "daydream?. "
                ),
                OptionEntity(
                    "memperhatikan detail lingkunganmu?",
                    "notice the details of your surroundings?. "
                ),
                27
            )
        )

        questions.add(
            QuestionEntity(
                39,
                "Apakah kamu cenderung ...",
                "Do you tend to... ",
                OptionEntity(
                    "ingin bekerja melalui konflik?",
                    "want to work through conflict??. "
                ),
                OptionEntity(
                    "menghindari konflik?",
                    "avoid conflict? "
                ),
                28
            )
        )

        questions.add(
            QuestionEntity(
                40,
                "Mana yang akan kamu pilih?",
                "Which would you choose? ",
                OptionEntity(
                    "Akurat",
                    "Accurate. "
                ),
                OptionEntity(
                    "Hampir tepat",
                    "Close enough. "
                ),
                29
            )
        )

        questions.add(
            QuestionEntity(
                41,
                "Pilih opsi yang paling menggambarkan dirimu:",
                "Choose the option that best describes you: ",
                OptionEntity(
                    "Keras",
                    "Loud. "
                ),
                OptionEntity(
                    "Berbicara dengan lembut",
                    "Soft spoken. "
                ),
                30
            )
        )

        questions.add(
            QuestionEntity(
                42,
                "Saat membuat lego, apakah kamu lebih suka...",
                "When building legos, do you prefer to... ",
                OptionEntity(
                    "membangun kreasi kamu sendiri?",
                    "build your own creation? "
                ),
                OptionEntity(
                    "mengikuti instruksi bangunan?",
                    "follow the building instructions?  "
                ),
                31
            )
        )

        questions.add(
            QuestionEntity(
                43,
                "Jika kamu sedang kesal dengan seorang teman, apakah kamu...",
                "If you are upset with a friend, would you... ",
                OptionEntity(
                    "langsung berbicara dengan teman kamu?",
                    "be direct and talk to your friend? "
                ),
                OptionEntity(
                    "menyimpan perasaanmu untuk dirimu sendiri?",
                    "keep your feelings to yourself?  "
                ),
                32
            )
        )

        questions.add(
            QuestionEntity(
                44,
                "Saat memainkan permainan papan, apakah kamu lebih suka...",
                "When playing a board game do you prefer to... ",
                OptionEntity(
                    "bermain dengan aturan kamu sendiri?",
                    "play by your own rules? "
                ),
                OptionEntity(
                    "mengikuti aturan dalam instruksi permainan?",
                    "strictly follow the rules in the game instructions?  "
                ),
                33
            )
        )

        questions.add(
            QuestionEntity(
                45,
                "Saat berada di bawah tekanan, apakah kamu cenderung...",
                "When under pressure do you tend to... ",
                OptionEntity(
                    "fokus?",
                    "focus and power through? "
                ),
                OptionEntity(
                    "tidak bisa fokus karena terlalu lelah?",
                    "can't focus because you're too frazzled?  "
                ),
                34
            )
        )

        return questions
    }
}