# Sliding
##### Minecraft Sliding Plugin
![GIF 2022-07-11 오후 3-59-41](https://user-images.githubusercontent.com/61282478/178207009-3f926e5b-e829-44d8-b038-2644335d471f.gif)

달리기 도중 쉬프트 두번을 눌러 슬라이딩을 할 수 있습니다.
config.yml을 수정하여 슬라이딩 거리, 딜레이 등등을 조절할 수 있습니다.

명령어 : /sliding 혹은 /슬라이딩 <리로드/reload>

config.yml
<br/>sliding-distance: 3 #슬라이딩을 하는 거리입니다. 최종적으로 슬라이딩을 하는 거리는 sliding-time값에 영향을 받습니다.
<br/>sliding-time: 10 #슬라이딩을 하는 시간입니다. 단위는 틱입니다.
<br/>sneak-interval: 500 #쉬프트 두번을 눌러 슬라이딩을 시행할때 쉬프트 두번을 누르는 시간의 간격입니다. 1000 = 1초입니다.
<br/>sliding-delay: 2000 #슬라이딩을 한 뒤 다음 슬라이딩을 사용할 수 있는 딜레이입니다. 1000 = 1초입니다.
<br/>require-running: true #뛰는 도중에 쉬프트 두번을 눌러 슬라이딩을 가능하게 합니다. false인경우 뛰지 않아도 슬라이딩이 가능합니다. 
